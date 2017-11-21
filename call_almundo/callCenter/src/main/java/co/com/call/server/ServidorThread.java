package co.com.call.server;

import co.com.call.empleados.dto.Empleado;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * servidor independiente que se dedica a administrar cada una de las llamadas
 * hechas por lo cliente.
 *
 * @author julio
 */
public class ServidorThread implements Runnable {

    private Socket socket;
    private ObjectOutputStream outputStream = null; // Salida
    private ObjectInputStream inputStream = null; // Entrada

    private List<Empleado> listOperadores;
    private List<Empleado> listSupervisores;
    private List<Empleado> listDirectores;
    private Semaphore semaphore;
    private Semaphore semaphoreMain;

    private List<Integer> listNumeroLlamadas;

    /**
     * Constructor
     *
     * @param socket
     * @param listOperadores
     * @param listSupervisores
     * @param listDirectores
     * @param listNumeroLlamadas
     * @param semaphore
     * @param semaphoreMain
     */
    public ServidorThread(Socket socket, List<Empleado> listOperadores,
            List<Empleado> listSupervisores,
            List<Empleado> listDirectores,
            List<Integer> listNumeroLlamadas,
            Semaphore semaphore, Semaphore semaphoreMain) {
        this.socket = socket;
        this.listOperadores = listOperadores;
        this.listSupervisores = listSupervisores;
        this.listDirectores = listDirectores;
        this.listNumeroLlamadas = listNumeroLlamadas;
        this.semaphore = semaphore;
        this.semaphoreMain = semaphoreMain;
        try {
            /**
             * Objeto para enviar mensaje al cliente
             */
            outputStream = new ObjectOutputStream(this.socket.getOutputStream());

            /**
             * Objeto para recibir mensaje del cliente
             */
            inputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getAnonymousLogger().warning(ex.getMessage());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().warning(ex.getMessage());
        }
    }

    /**
     * Cierra las conexiones creadas
     */
    public void desconectar() {
        try {
            if (socket != null) {
                socket.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ex) {
            Logger.getAnonymousLogger().warning(ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            int myRandomNumber;
            myRandomNumber = r.nextInt(10 - 5 + 1) + 5;

            semaphore.acquire();
            String empleado = "";
            while (listNumeroLlamadas.size() > 0) {
                semaphoreMain.acquire();
                // aqui hay llamadas por atender, quien la atiende
                if (!listOperadores.isEmpty()) {
                    // la atiende un operador
                    listNumeroLlamadas.remove(0);
                    empleado = "operador";
                    Empleado empleadoOperador = listOperadores.remove(0);
                    empleadoOperador.start();
                    break;
                } else if (!listSupervisores.isEmpty()) {
                    // la atiende un supervisor
                    listNumeroLlamadas.remove(0);
                    empleado = "supervisor";
                    Empleado empleadoSupervisor = listSupervisores.remove(0);
                    empleadoSupervisor.start();
                    break;
                } else if (!listDirectores.isEmpty()) {
                    // la atiende un director
                    listNumeroLlamadas.remove(0);
                    empleado = "Director";
                    Empleado empleadoDirector = listDirectores.remove(0);
                    empleadoDirector.start();
                    break;
                } else {
                    semaphoreMain.release();
                }
            }

            /**
             * Enviando json al cliente
             */
            outputStream.writeObject("LLamada atendida por el empleado " + empleado + " Con una duración de " + myRandomNumber + " segundos");
            outputStream.flush();
            /**
             * Mensaje recibido del cliente
             */
            String mensajeRecibido = inputStream.readObject().toString();
            System.out.println("Servidor recibe llamada del numero de telefono -> " + mensajeRecibido);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getAnonymousLogger().warning(ex.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
        }

        semaphore.release();

    }
}

package co.com.call.server;

import co.com.call.dispatcher.Main;
import co.com.call.empleados.dto.Empleado;
import co.com.call.empleados.dto.Operador;
import co.com.call.respuestas.dto.RespuestaServidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
    private RespuestaServidor respuestaServidor;

    private List<Empleado> listOperadores;
    private List<Empleado> listSupervisores;
    private List<Empleado> listDirectores;
    private Semaphore semaphore;

    private List<Integer> listNumeroLlamadas;

    /**
     * Constructor
     *
     * @param socket
     * @param respuestaServidor
     */
    public ServidorThread(Socket socket, List<Empleado> listOperadores,
            List<Empleado> listSupervisores,
            List<Empleado> listDirectores,
            List<Integer> listNumeroLlamadas,
            Semaphore semaphore) {
        this.socket = socket;
        this.listOperadores = listOperadores;
        this.listSupervisores = listSupervisores;
        this.listDirectores = listDirectores;
        this.listNumeroLlamadas = listNumeroLlamadas;
        this.semaphore = semaphore;
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
            int myRandomNumber = 0;
            myRandomNumber = r.nextInt(10 - 5 + 1) + 5;
            semaphore.acquire();
            String empleado = "";
            while (listNumeroLlamadas.size() > 0) {
                // aqui hay llamadas por atender, quien la atiende
                if (!listOperadores.isEmpty()) {
                    // la atiende un operador
                    listNumeroLlamadas.remove(0);
                    empleado = "operador";
                    listOperadores.remove(0);
                    if (listSupervisores.isEmpty()) {
                        Empleado supervisor = new Empleado();
                        listSupervisores.add(supervisor);
                        supervisor = new Empleado();
                        listSupervisores.add(supervisor);
                        supervisor = new Empleado();
                        listSupervisores.add(supervisor);
                    }

                    if (listDirectores.isEmpty()) {
                        Empleado director = new Empleado();
                        listDirectores.add(director);
                        director = new Empleado();
                        listDirectores.add(director);
                        director = new Empleado();
                        listDirectores.add(director);
                    }

                    break;
                } else if (!listSupervisores.isEmpty()) {
                    // la atiende un supervisor
                    listNumeroLlamadas.remove(0);
                    empleado = "supervisor";
                    listSupervisores.remove(0);
                    if (listOperadores.isEmpty()) {
                        Empleado operador = new Operador();
                        listOperadores.add(operador);
                        operador = new Operador();
                        listOperadores.add(operador);
                        operador = new Operador();
                        listOperadores.add(operador);
                    }

                    if (listDirectores.isEmpty()) {
                        Empleado director = new Empleado();
                        listDirectores.add(director);
                        director = new Empleado();
                        listDirectores.add(director);
                        director = new Empleado();
                        listDirectores.add(director);
                        semaphore = new Semaphore(1);
                    }

                    break;
                } else if (!listDirectores.isEmpty()) {
                    // la atiende un director
                    listNumeroLlamadas.remove(0);
                    empleado = "Director";
                    listDirectores.remove(0);
                    if (listOperadores.isEmpty()) {
                        Empleado operador = new Operador();
                        listOperadores.add(operador);
                        operador = new Operador();
                        listOperadores.add(operador);
                        operador = new Operador();
                        listOperadores.add(operador);
                    }

                    if (listSupervisores.isEmpty()) {
                        Empleado supervisor = new Empleado();
                        listSupervisores.add(supervisor);
                        supervisor = new Empleado();
                        listSupervisores.add(supervisor);
                        supervisor = new Empleado();
                        listSupervisores.add(supervisor);
                    }
                    break;
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
            try {
                Thread.sleep(myRandomNumber * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getAnonymousLogger().warning(ex.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectar();
        }

        semaphore.release();

    }

    /**
     * metodo que maneja la diponibilidad de cada uno de los empleados
     *
     * @param empleado
     */
    private void setDisponibilidadEmpleado(Empleado empleado) {
        Main.LIST_EMPLEADO_DISPONIBLIDAD.entrySet().forEach((empleadoDisponible) -> {
            Empleado empleadoDisponibilidad = empleadoDisponible.getKey();
            Boolean isDisponible = empleadoDisponible.getValue();
            if (empleadoDisponibilidad == empleado) {
                Main.LIST_EMPLEADO_DISPONIBLIDAD.put(empleadoDisponibilidad, true);
            }
        });
    }
}

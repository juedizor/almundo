package co.com.call.server;

import co.com.call.dispatcher.Main;
import co.com.call.empleados.dto.Empleado;
import co.com.call.respuestas.dto.RespuestaServidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
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
    private Empleado empleado;

    /**
     * Constructor
     *
     * @param socket
     * @param respuestaServidor
     */
    public ServidorThread(Socket socket, RespuestaServidor respuestaServidor, Empleado empleado) {
        this.socket = socket;
        this.respuestaServidor = respuestaServidor;
        this.empleado = empleado;
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
            respuestaServidor.setMensaje("LLamada atendida por el empleado " + empleado.getClass().getSimpleName() + " Con una duración de " + myRandomNumber + " segundos");

            /**
             * Enviando json al cliente
             */
            outputStream.writeObject(respuestaServidor.getMensaje());
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
        } finally {
            desconectar();
            setDisponibilidadEmpleado(empleado);
        }
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

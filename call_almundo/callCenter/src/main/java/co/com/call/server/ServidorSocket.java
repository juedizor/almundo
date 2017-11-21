package co.com.call.server;

import co.com.call.dispatcher.Main;
import co.com.call.empleados.dto.Director;
import co.com.call.empleados.dto.Empleado;
import co.com.call.empleados.dto.Operador;
import co.com.call.empleados.dto.Supervisor;
import co.com.call.respuestas.dto.RespuestaServidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Servidor en donde se atienden cada una de las solicitudes, en este caso las
 * llamadas hechas por los clientes. La clase tiene la posibilidad de antender
 * cualquier cantidad de llamadas al tiempo.
 *
 *
 */
public class ServidorSocket {

    public ServidorSocket() {
    }

    /**
     * Constructor
     *
     * @param serverSocket
     * @param respuestaServidor
     */
    public void conectar(ServerSocket serverSocket, RespuestaServidor respuestaServidor) {
        try {
            System.out.println("Conectado servidor");
            /**
             * Esperando respuesta
             */
            Socket socket = serverSocket.accept();
            System.out.println("Nueva llamada Entrante");

            Empleado empleado = getEmpleadoDisponible();
            if (empleado != null) {
                // como hay empleado disponible, realiza la atencion de la llamada
                Thread thread = new Thread(new ServidorThread(socket, new RespuestaServidor(), empleado));
                thread.start();
            }

        } catch (IOException ex) {
            Logger.getAnonymousLogger().warning(ex.getMessage());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().warning(ex.getMessage());
        }
    }

    private Empleado getEmpleadoDisponible() {
        for (Map.Entry<Empleado, Boolean> empleado : Main.LIST_EMPLEADO_DISPONIBLIDAD.entrySet()) {
            Empleado empleadoDisponibilidad = empleado.getKey();
            Boolean isDisponible = empleado.getValue();
            if (empleadoDisponibilidad instanceof Operador) {
                if (isDisponible) {
                    Empleado operador = (Operador) empleadoDisponibilidad;
                    Main.LIST_EMPLEADO_DISPONIBLIDAD.replace(operador, true, false);
                    return operador;
                }
            }
            if (empleadoDisponibilidad instanceof Supervisor) {
                if (isDisponible) {
                    Empleado supervisor = (Supervisor) empleadoDisponibilidad;
                    Main.LIST_EMPLEADO_DISPONIBLIDAD.replace(supervisor, true, false);
                    return supervisor;
                }
            }
            if (empleadoDisponibilidad instanceof Director) {
                if (isDisponible) {
                    Empleado director = (Director) empleadoDisponibilidad;
                    Main.LIST_EMPLEADO_DISPONIBLIDAD.replace(director, true, false);
                    return director;
                }
            }
        }
        return null;
    }

}

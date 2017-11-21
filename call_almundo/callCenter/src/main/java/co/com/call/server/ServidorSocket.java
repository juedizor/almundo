package co.com.call.server;

import co.com.call.empleados.dto.Director;
import co.com.call.empleados.dto.Empleado;
import co.com.call.empleados.dto.Operador;
import co.com.call.empleados.dto.Supervisor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 * Servidor en donde se atienden cada una de las solicitudes, en este caso las
 * llamadas hechas por los clientes. La clase tiene la posibilidad de antender
 * cualquier cantidad de llamadas al tiempo.
 *
 *
 */
public class ServidorSocket {

    private List<Integer> listNumeroLlamadas;
    private static int contadorLlamadas;
    private Semaphore semaphore;
    private Semaphore semaphoreMain;

    private List<Empleado> listOperadores;
    private List<Empleado> listSupervisors;
    private List<Empleado> listDirectores;

    public ServidorSocket(Semaphore semaphoreMain) {
        listOperadores = new ArrayList<>();
        Empleado operador = new Operador(listOperadores, semaphoreMain);
        listOperadores.add(operador);

        listSupervisors = new ArrayList<>();
        Empleado supervisor = new Supervisor(listSupervisors, semaphoreMain);
        listSupervisors.add(supervisor);
        supervisor = new Empleado();
        listSupervisors.add(supervisor);
        supervisor = new Empleado();
        listSupervisors.add(supervisor);

        listDirectores = new ArrayList<>();
        Empleado director = new Director(listDirectores, semaphoreMain);
        listDirectores.add(director);
        director = new Empleado();
        listDirectores.add(director);
        director = new Empleado();
        listDirectores.add(director);
        semaphore = new Semaphore(1);
        this.semaphoreMain = semaphoreMain;

    }

    /**
     * Constructor
     *
     * @param serverSocket
     */
    public void conectar(ServerSocket serverSocket) {
        try {
            System.out.println("Conectado servidor");
            listNumeroLlamadas = new LinkedList<>();
            /**
             * Esperando respuesta
             */
            Socket socket = serverSocket.accept();
            listNumeroLlamadas.add(contadorLlamadas++);
            Thread thread = new Thread(new ServidorThread(socket, listOperadores, 
                    listSupervisors, listDirectores, listNumeroLlamadas, semaphore, semaphoreMain));
            thread.start();
        } catch (IOException ex) {
            Logger.getAnonymousLogger().warning(ex.getMessage());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().warning(ex.getMessage());
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.call.server;

import co.com.call.respuestas.dto.RespuestaServidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 * clase encargada de generar el server
 *
 * @author julio
 */
public class IniciarServer {

    private final ServidorSocket myServidor;
    private RespuestaServidor respuestaServidor;
    private final Semaphore semaphoreMain;

    public IniciarServer(int numeroHilosSimultaneos) {
        semaphoreMain = new Semaphore(numeroHilosSimultaneos);
        myServidor = new ServidorSocket(semaphoreMain);

    }

    /**
     * Ejecuta el proceso
     */
    public void arrancarServidor() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5002);
        } catch (IOException e) {
            Logger.getAnonymousLogger().warning(e.getMessage());
        }
        while (true) {
            myServidor.conectar(serverSocket);
        }
    }

}

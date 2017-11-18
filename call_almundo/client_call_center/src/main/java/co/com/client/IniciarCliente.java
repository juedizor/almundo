/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Clase de simular una prueba unitaria para el envio de llamadas al tiempo al
 * servidor, en este caso se realiza la ejecucion de diez hilos simultaneos
 *
 * @author julio
 */
public class IniciarCliente {

    private ClientSocket myClient;
    private final ExecutorService executor; // usado para la ejecucion concurrente de tareas
    private static final int CANT_MAX_THREAD = 10; // numero de hilos simultaneos soportados
    private static final int TAREAS_MAXIMAS = 10; // cantidad de tareas soportadas

    public IniciarCliente() {
        ArrayBlockingQueue<Runnable> waitqueue = new ArrayBlockingQueue<>(CANT_MAX_THREAD);
        this.executor = new ThreadPoolExecutor(CANT_MAX_THREAD,
                TAREAS_MAXIMAS, 1, TimeUnit.SECONDS, waitqueue);
    }

    public void iniciarCliente() throws InterruptedException {
        for (int i = 0; i < CANT_MAX_THREAD; i++) {
            myClient = new ClientSocket();
            executor.submit(myClient);
        }
        executor.shutdown();
    }

}

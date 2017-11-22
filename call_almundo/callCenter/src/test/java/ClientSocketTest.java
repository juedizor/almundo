/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.com.call.server.IniciarServer;
import co.com.client.IniciarCliente;
import co.com.client.dto.LlamadaCliente;
import co.com.client.util.Utils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.logging.Logger;
import net.jodah.concurrentunit.Waiter;

/**
 *
 * @author ASD
 */
public class ClientSocketTest {

    IniciarCliente cliente;
    private static ClientSocket myClient;
    private static ExecutorService executor; // usado para la ejecucion concurrente de tareas
    private static final int CANT_MAX_THREADS = 10; // numero de hilos simultaneos soportados
    private static final int TAREAS_EJECUTAR = 15;

    public ClientSocketTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        new Thread(() -> {
            new IniciarServer().arrancarServidor();
        }).start();

        ArrayBlockingQueue<Runnable> waitqueue = new ArrayBlockingQueue<>(CANT_MAX_THREADS);
        executor = new ThreadPoolExecutor(CANT_MAX_THREADS,
                TAREAS_EJECUTAR, 1, TimeUnit.SECONDS, waitqueue);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void atenderLlamadas() {
        try {
            final Waiter waiter = new Waiter();
            for (int i = 0; i < TAREAS_EJECUTAR; i++) {
                
                Thread hilo = new Thread(() -> {
                    waiter.assertTrue(true);
                    waiter.resume();
                    Client client = new Client();
                    String res = client.response();
                    System.out.println(res);
                });
                executor.submit(hilo);
                
            }
            waiter.await(0);
            executor.shutdown();
        } catch (TimeoutException ex) {
            Logger.getLogger(ClientSocketTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class ClientSocket implements Runnable {

        private Socket socket; // Conexión
        private ObjectOutputStream outputStream = null; // Salida
        private ObjectInputStream inputStream = null; // Entrada

        private void conectar() {
            try {
                /**
                 * Se envia una llamada al servidor de un numero aleatorio
                 */
                LlamadaCliente llamadaCliente = new LlamadaCliente();
                llamadaCliente.setNumeroTelefono(new Random().nextInt(500000) + "");
                System.out.println("Cliente envia -> " + llamadaCliente.getNumeroTelefono() + " hora " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

                /**
                 * Conexión servidor
                 */
                socket = new Socket(Utils.MI_IP, Utils.PUERTO);

                /**
                 * Objeto para enviar mensaje al servidor
                 */
                outputStream = new ObjectOutputStream(socket.getOutputStream());

                /**
                 * Objeto para recibir mensaje del servidor
                 */
                inputStream = new ObjectInputStream(socket.getInputStream());

                /**
                 * Capturamos la respuesta enviada por el servidor, relacionada
                 * a la llamada hecha anteriormente
                 */
                String mensajeRecibido = inputStream.readObject().toString();
                System.out.println("Cliente recibe -> " + mensajeRecibido + " numero de telefono " + llamadaCliente.getNumeroTelefono());

                /**
                 * Enviando la llamada al servidor
                 */
                outputStream.writeObject(llamadaCliente.getNumeroTelefono());
                outputStream.flush();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getAnonymousLogger().warning(ex.getMessage());
            } finally {
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
                } catch (Exception ex) {
                    Logger.getAnonymousLogger().warning(ex.getMessage());
                }
            }
        }

        @Override
        public void run() {
            conectar();
        }

    }

}

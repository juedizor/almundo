/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.com.call.server.IniciarServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 *
 */
public class ClientSocketTest {

    private ClientSocket myClient;
    private static ExecutorService executor; // usado para la ejecucion concurrente de tareas
    private static final int CANT_MAX_THREADS = 10; // numero de hilos simultaneos soportados
    private static final int TAREAS_EJECUTAR = 15;

    public ClientSocketTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        new Thread(() -> {
            new IniciarServer(CANT_MAX_THREADS).arrancarServidor();
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
    public void atenderLlamadas() throws InterruptedException, ExecutionException {
        for (int i = 0; i < TAREAS_EJECUTAR; i++) {
            myClient = new ClientSocket();
            Future<String> f1 = executor.submit(myClient);
            String v = f1.get();
            Assert.assertNotNull(v);
        }
        executor.shutdown();
    }

    class LlamadaCliente {

        private String numeroTelefono;

        public LlamadaCliente() {

        }

        public String getNumeroTelefono() {
            return numeroTelefono;
        }

        public void setNumeroTelefono(String numeroTelefono) {
            this.numeroTelefono = numeroTelefono;
        }

    }

    class Utils {

        public final static int PUERTO = 5002;
        public final static String MI_IP = "localhost";

    }

    class ClientSocket implements Callable<String> {

        private Socket socket; // Conexión
        private ObjectOutputStream outputStream = null; // Salida
        private ObjectInputStream inputStream = null; // Entrada

        private String conectar() {
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
                return mensajeRecibido;
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

            return "";
        }

        @Override
        public String call() throws Exception {
            return conectar();
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.client;

import co.com.client.dto.LlamadaCliente;
import co.com.client.util.Utils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Logger;

/**
 * clase principal encargada de realizar la conexion y la realizacion de
 * llamadas hacia el server
 *
 * @author julio
 */
public class ClientSocket implements Runnable {

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
             * Capturamos la respuesta enviada por el servidor, relacionada a la
             * llamada hecha anteriormente
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

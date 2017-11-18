/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.client.main;

import co.com.client.IniciarCliente;

/**
 * Inicio de la aplicacion cliente para el envio de las llamadas al server
 *
 * @author julio
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        new IniciarCliente().iniciarCliente();
    }

}

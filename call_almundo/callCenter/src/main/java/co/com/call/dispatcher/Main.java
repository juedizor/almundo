/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.call.dispatcher;

import co.com.call.server.IniciarServer;

/**
 * clase inicial en donde se simula la cantidad de empleados disponibles, se
 * manejan objetos a traves de herencia para modelar los tipos de empleados En
 * el metodo Main, se inicia la aplicacion
 *
 * @author julio
 */
public class Main {

    public static void main(String[] args) {
        new IniciarServer().arrancarServidor(); // inicio del servidor para la atencion de llamadas

    }

}

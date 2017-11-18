/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.call.dispatcher;

import co.com.call.dispatcher.enums.CantidadEmpleados;
import co.com.call.empleados.dto.Director;
import co.com.call.empleados.dto.Empleado;
import co.com.call.empleados.dto.Operador;
import co.com.call.empleados.dto.Supervisor;
import co.com.call.server.IniciarServer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * clase inicial en donde se simula la cantidad de empleados disponibles, se
 * manejan objetos a traves de herencia para modelar los tipos de empleados
 * En el metodo Main, se inicia la aplicacion
 * @author julio
 */
public class Main {

    public static final ConcurrentMap<Empleado, Boolean> LIST_EMPLEADO_DISPONIBLIDAD;

    static {
        LIST_EMPLEADO_DISPONIBLIDAD = new ConcurrentHashMap<>();

        /**
         * Crea la cantidad de empleados OPERADOR
         */
        for (int i = 0; i < CantidadEmpleados.OPERADOR.getCantidad(); i++) {
            Empleado empleado = new Operador();
            LIST_EMPLEADO_DISPONIBLIDAD.put(empleado, true);
        }

        /**
         * Crea la cantidad de empleados SUPERVISOR
         */
        for (int i = 0; i < CantidadEmpleados.SUPERVISOR.getCantidad(); i++) {
            Empleado empleado = new Supervisor();
            LIST_EMPLEADO_DISPONIBLIDAD.put(empleado, true);
        }

        /**
         * Crea la cantidad de empleados DIRECTORES
         */
        for (int i = 0; i < CantidadEmpleados.DIRECTOR.getCantidad(); i++) {
            Empleado empleado = new Director();
            LIST_EMPLEADO_DISPONIBLIDAD.put(empleado, true);
        }

    }

    public static void main(String[] args) {
        new IniciarServer().arrancarServidor(); // inicio del servidor para la atencion de llamadas

    }

}

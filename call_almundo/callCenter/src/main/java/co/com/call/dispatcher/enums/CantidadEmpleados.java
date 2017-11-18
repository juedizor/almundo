/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.call.dispatcher.enums;

/**
 * enum para parametrizar la cantidad de empleados disponibles que se tienen 
 * dentro del call center
 * @author julio
 */
public enum CantidadEmpleados {

    OPERADOR(3),
    SUPERVISOR(3),
    DIRECTOR(3);

    private int cantidad;

    private CantidadEmpleados(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.call.empleados.dto;

import static java.lang.Thread.sleep;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * clase con relacion al empleado operador
 *
 * @author julio.izquierdo
 */
public class Operador extends Empleado {

    private final List<Empleado> listOperadores;
    private final Semaphore callCenterResources;

    public Operador(List<Empleado> listOperadores, Semaphore callCenterResources) {
        this.listOperadores = listOperadores;
        this.callCenterResources = callCenterResources;
    }

    @Override
    public void run() {
        int callTime = (int) (Math.random() * (10 - 5)) + 5;
        try {
            sleep(callTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Release Lock
            callCenterResources.release();
            this.listOperadores.add(new Operador(this.listOperadores, this.callCenterResources));
        }

    }

}

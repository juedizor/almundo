/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.call.empleados.dto;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 * clase del empleado director
 *
 * @author julio.izquierdo
 */
public class Director extends Empleado {

    private final List<Empleado> listDirectores;
    private final Semaphore callCenterResources;

    public Director(List<Empleado> listDirectores, Semaphore callCenterResources) {
        this.listDirectores = listDirectores;
        this.callCenterResources = callCenterResources;
    }

    @Override
    public void run() {
        int callTime = (int) (Math.random() * (10 - 5)) + 5;
        try {
            sleep(callTime * 1000);
        } catch (InterruptedException e) {
            Logger.getAnonymousLogger().warning(e.getMessage());
        } finally {
            // Release Lock
            callCenterResources.release();
            this.listDirectores.add(new Director(this.listDirectores, this.callCenterResources));
        }

    }

}

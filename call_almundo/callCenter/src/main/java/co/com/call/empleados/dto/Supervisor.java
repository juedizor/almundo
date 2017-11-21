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
 * clase con relacion al empleado supervisor
 *
 * @author julio.izquierdo
 */
public class Supervisor extends Empleado {

    private final List<Empleado> listSupervisores;
    private final Semaphore callCenterResources;

    public Supervisor(List<Empleado> listSupervisores, Semaphore callCenterResources) {
        this.listSupervisores = listSupervisores;
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
            this.listSupervisores.add(new Supervisor(this.listSupervisores, this.callCenterResources));
        }

    }

}

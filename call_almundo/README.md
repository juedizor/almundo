# call_center_almundo_sockets

Explicacion: 

1. Se crean la simulación del call center a traves de sockets de java y ejecucion de hilos. 
2. Se generan dos proyectos uno para la creacion del server el cual recibe las llamadas de los clientes, y asi mismo un proyecto cliente 
de donde se envian las llamadas simultaneas al server para que sean atendidas.

 
	
Tener en cuenta en la ejecucion: 

1. Primero ejecutar el proyecto callCenter en el cual se debe ejecutar a traves de la clase co.com.call.dispatcher.Main que es donde se 
inicia el server para la atencion de llamadas. 

package co.com.call.dispatcher;

import co.com.call.server.IniciarServer;

public class Main {
    
    private static final int NUM_HILOS_SIMULTANEOS = 10;

    public static void main(String[] args) {
        new IniciarServer(NUM_HILOS_SIMULTANEOS).arrancarServidor(); // inicio del servidor para la atencion de llamadas

    }

}

Se visualiza la variable NUM_HILOS_SIMULTANEOS que es la cantidad de hilos que aceptaria a la vez para atencio de llamadas el servidor. 


2. Luego ejecutar el proyecto client_call_center en donde se debe ejecutar la clase co.com.client.main.Main que es la que se encarga de envia
10 solicitudes simultaneas al server para ser atendidas. 



package co.com.client.main;

import co.com.client.IniciarCliente;

/**
 * Inicio de la aplicacion cliente para el envio de las llamadas al server
 *
 * @author julio
 */
public class Main {
    
    private static final int CANT_LLAMADAS_AL_TIEMPO = 10;
    private static final int CANT_TAREAS_PERMITIDAS = 10;

    public static void main(String[] args) throws InterruptedException {
        new IniciarCliente(CANT_LLAMADAS_AL_TIEMPO, CANT_TAREAS_PERMITIDAS).iniciarCliente();
    }

}

Con esta clase se inicia el cliente, en donde se le indica que seran 10 como numero de llamadas que se enviaran al tiempo al server.


- Se debe tener en cuenta que toda la ejecucion se realiza a traves de consola, por tanto los resultados se visualizaran en la misma. 
- Una vez se hayan hecho la ejecucion de la manera indicada anteriormente con los parametros que estan actualmente se debe visualizar 
la atencion de las 10 llamadas de la siguiente manera en consola.


Cliente envia -> 16810 hora 2017-11-21T21:15:14.092
Cliente envia -> 53084 hora 2017-11-21T21:15:14.092
Cliente envia -> 191714 hora 2017-11-21T21:15:14.092
Cliente envia -> 259182 hora 2017-11-21T21:15:14.092
Cliente envia -> 125772 hora 2017-11-21T21:15:14.092
Cliente envia -> 443961 hora 2017-11-21T21:15:14.092
Cliente envia -> 333468 hora 2017-11-21T21:15:14.092
Cliente envia -> 207885 hora 2017-11-21T21:15:14.098
Cliente envia -> 27609 hora 2017-11-21T21:15:14.092
Cliente envia -> 453738 hora 2017-11-21T21:15:14.092
Cliente recibe -> LLamada atendida por el empleado operador Con una duración de 8 segundos numero de telefono 27609
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 7 segundos numero de telefono 443961
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 6 segundos numero de telefono 191714
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 7 segundos numero de telefono 207885
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 5 segundos numero de telefono 53084
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 7 segundos numero de telefono 125772
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 6 segundos numero de telefono 259182
Cliente recibe -> LLamada atendida por el empleado operador Con una duración de 10 segundos numero de telefono 333468
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 9 segundos numero de telefono 453738
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 7 segundos numero de telefono 16810


- Se visualizara el cliente enviado llamadas de manera simultanea, y el cliente recibiendo quien atendio su llamada y que tiempo, se demoro en atenderla. 


- En caso que se enviaran mas de 10 llamadas,  el cliente usa un ExecutorService con un pool de hilos para que envie solo los disponibles, 
y luego los demas los mantiene en una cola, para cuando detecte que termina la ejecucion de alguno de los enviados comience con estos. 
Por tanto si se envia mas de 10 llamadas a traves de la variable: 

- private static final int CANT_TAREAS_PERMITIDAS = 15; // en este caso 15 llamadas

Para las 15 el cliente primero enviara diez y teniendo en cuenta la disponibilidad se iran enviando las demas, al ejeuctar nuevamente los proyectos de la siguiente manera se vera algo como esto: 


Cliente envia -> 309190 hora 2017-11-21T21:23:23.246
Cliente envia -> 207357 hora 2017-11-21T21:23:23.246
Cliente envia -> 156330 hora 2017-11-21T21:23:23.246
Cliente envia -> 176405 hora 2017-11-21T21:23:23.246
Cliente envia -> 157822 hora 2017-11-21T21:23:23.246
Cliente envia -> 321985 hora 2017-11-21T21:23:23.246
Cliente envia -> 24166 hora 2017-11-21T21:23:23.246
Cliente envia -> 152663 hora 2017-11-21T21:23:23.246
Cliente envia -> 137480 hora 2017-11-21T21:23:23.246
Cliente envia -> 236501 hora 2017-11-21T21:23:23.246
Cliente recibe -> LLamada atendida por el empleado operador Con una duración de 8 segundos numero de telefono 176405
Cliente envia -> 474927 hora 2017-11-21T21:23:23.372
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 10 segundos numero de telefono 207357
Cliente envia -> 495568 hora 2017-11-21T21:23:23.373
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 7 segundos numero de telefono 321985
Cliente envia -> 265804 hora 2017-11-21T21:23:23.373
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 6 segundos numero de telefono 495568
Cliente envia -> 407446 hora 2017-11-21T21:23:23.375
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 6 segundos numero de telefono 137480
Cliente envia -> 458500 hora 2017-11-21T21:23:23.379
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 5 segundos numero de telefono 152663
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 10 segundos numero de telefono 236501
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 8 segundos numero de telefono 156330
Cliente recibe -> LLamada atendida por el empleado operador Con una duración de 9 segundos numero de telefono 157822
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 10 segundos numero de telefono 24166
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 8 segundos numero de telefono 309190
Cliente recibe -> LLamada atendida por el empleado operador Con una duración de 5 segundos numero de telefono 474927
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 10 segundos numero de telefono 265804
Cliente recibe -> LLamada atendida por el empleado supervisor Con una duración de 6 segundos numero de telefono 407446
Cliente recibe -> LLamada atendida por el empleado operador Con una duración de 9 segundos numero de telefono 458500


- Donde se visualiza la atencion de cada llamada, por tanto queda solucionado si se envian mas de 10 llamadas y quedan pendientes. 
- Otro punto importante dentro de la prueba era si ya no se encontraban empleados disponibles, este manejo lo realiza el server, dentro 
de cada uno de los hilos para atender cada una de las llamadas. Dentro del server se manejan hilos independientes y si llegase un momento 
de no poder atender una llamada por parte del alguno de los empleados esta queda esperando hasta que se desocupe alguno, por esto se maneja un a lista donde se van encolando cada una de estas. 








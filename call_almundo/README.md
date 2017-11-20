# call_center_almundo_sockets

Explicacion: 

1. Se crean la simulación del call center a traves de sockets de java y ejecucion de hilos. 
2. Se generan dos proyectos uno para la creacion del server el cual recibe las llamadas de los clientes, y asi mismo un proyecto cliente 
de donde se envian las llamadas simultaneas al server para que sean atendidas. 

 
	
Tener en cuenta en la ejecucion: 

1. Primero ejecutar el proyecto callCenter en el cual se debe ejecutar a traves de la clase co.com.call.dispatcher.Main que es donde se 
inicia el server para la atencion de llamadas. 
2. Luego ejecutar el proyecto client_call_center en donde se debe ejecutar la clase co.com.client.main.Main que es la que se encarga de envia
10 solicitudes simultaneas al server para ser atendidas. 

Al ejecutar la app, sobre todo la del cliente que es la que envia las llamadas simultaneas al server, el resultado debe asemejarse a algo como lo siguiente: 

 - Cliente envia -> 217470 hora 2017-11-18T14:38:46.411
 - Cliente envia -> 4097 hora 2017-11-18T14:38:46.411
 - Cliente envia -> 337188 hora 2017-11-18T14:38:46.411
 - Cliente envia -> 111604 hora 2017-11-18T14:38:46.411
 - Cliente envia -> 362353 hora 2017-11-18T14:38:46.411
 - Cliente envia -> 73805 hora 2017-11-18T14:38:46.411
 - Cliente envia -> 473208 hora 2017-11-18T14:38:46.409
Cliente envia -> 139261 hora 2017-11-18T14:38:46.411
Cliente envia -> 316554 hora 2017-11-18T14:38:46.409
Cliente envia -> 107372 hora 2017-11-18T14:38:46.409
Cliente recibe -> LLamada atendida por el empleado Operador Con una duración de 10 segundos numero de telefono 316554
Cliente recibe -> LLamada atendida por el empleado Operador Con una duración de 7 segundos numero de telefono 217470
Cliente recibe -> LLamada atendida por el empleado Supervisor Con una duración de 8 segundos numero de telefono 139261
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 6 segundos numero de telefono 111604
Cliente recibe -> LLamada atendida por el empleado Operador Con una duración de 6 segundos numero de telefono 4097
Cliente recibe -> LLamada atendida por el empleado Supervisor Con una duración de 9 segundos numero de telefono 107372
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 5 segundos numero de telefono 473208
Cliente recibe -> LLamada atendida por el empleado Supervisor Con una duración de 5 segundos numero de telefono 73805
Cliente recibe -> LLamada atendida por el empleado Director Con una duración de 10 segundos numero de telefono 362353

 - las diez primeras lineas son las 10 solicitudes simultaneas en cuanto a llamadas hacia el server
 - las tras diez son las respuestas del server hacia el cliente. 



# almundo_test

Instalacion: 

1. Se tiene que clonar el repositorio de git siguiente: https://github.com/juedizor/almundo.git
a traves de la instruccion git clone. 

2. una vez se tenga el proyecto, se pueden evidenciar tres directorios. 
	- api_node_almundo
		proyecto donde se encuentra la api rest desarrollada. 
	- gui-angular-almundo
		proyecto donde se encuentra la aplicacion front desarrollada para el consumo de la api anterior
	- call_almundo
		proyecto java en donde se evidencia la simulacion del call center, utilizando socket e hilos. 
git pu
3. Para la ejecucion de la api se debe tener en cuenta los siguientes pasos: 
	- Entrar al directorio a traves de la consola de preferencia 
	- Una vez ubicados en la ruta realizar un movimiento a la ruta /app 
	- En esta ubicacion se debe realizar la instalacion de paquetes a traves de npm, tener en cuenta que se debe tener
	instalado. 
	- Tener en cuenta que nodejs se debe tener instalado en donde se va a realizar la instalacion. 
	- Listo, ahora una vez se tenga esto verificado se debe realizar la instalacion indicando en la consola }
	npm install
	- una vez termine y se aseguren que dicha instalacion esta completada se debe realizar el inicio del api
	a traves del comando node app.js
	- si se ejecuta de esta manera, se iniciara el api en el puerto 3000, esto por el archivo config.js que se encuentra
	dentro del proyecto en donde se tienen configurados para ambientes de produccion y por default. 
	- si se requiere ejecutar en el puerto de produccion se debe indicar el siguiente comando NODE_ENV='production' node app.js
     	- una vez se realizan esto pasos el api queda expuesta a traves del puerto configurado. 

4. Para la ejecucion de la aplicacion desarrollada para el consumo de la api se debe tener en cuenta lo siguiente: 

	- Entrar al directorio gui-angular-almundo a traves de la consola de preferencia 
	- Al estar seguro de estar en esta ruta, se deben instalar las librerias a traves de npm y bower
	- Estas deben estar instaladas con anterioridad, en la instalacion de la api rest, si fue exitosa. 
	- Digitar en consola npm install, esto instalara herramientas de gulp con la finalidad de testear la app en un 
	servidor local. 
	- luego digital en consola bower install, esto con la finalidad de instalar todas las librerias necesarias para su 
	correcto funcionamiento, en este caso angular, bootstrap, etc. 
	- una vez realizado este paso digitar en consola el comando gulp. 
	- Este realizara el levantamiento de la aplicacion en un server local, para este caso en http://localhost:8000/#!/
	- Y con esto se puede realizar la visualizacion de la web donde se listan los hoteles su busqueda correspondiente y 
	los filtros, todos comunicandose con la app api rest. 
	- Se debe tener en cuenta el puerto en donde se esta ejecutando el api para la correcta comunicacion de la aplicacion 
	angular. 
	- para modificar la api a la cual se comunica la app angular se debe modificar en el archivo siguiente: 
		app.js - linea http://localhost:3000/, donde se vea el 3000 se cambia por el puerto de la api. 

	- Ademas dentro de la app se tiene un directorio Dist en el cual se generaron los .min de los css y del codigo js 
	entonces para ejecutar la aplicacion desde aqui se debe:
		ejecutar el comando en consola: gulp prodServer, y esta tomara y desplegara los archivos desde el 
		directorio Dist. Para verificar se debe revisar los resources que muestran los navegadores, en donde se evidencia
		el llamado a los archivos .min.css y .min.js



	

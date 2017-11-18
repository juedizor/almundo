/**
  hotelesController.js, encargado de la comunicacion con el modelo de datos,
  en este caso la lectura del archivo data.json
*/
var jsonQuery = require ("json-query");
var parser = require('json-parser');


var hotelModel = require("../models/hotelsModel.js"); // require del modulo de lectura de datos del archivo data.json

var dataHoteles = hotelModel.findAll; // capturamos en una variable todo lo creado en el modulo

/**
  retorna todos los hoteles, teniendo en cuenta la comunicacion con el model
*/
function listarHoteles(){
  var dataJsonHotel = dataHoteles();
  var jsonParse = JSON.parse(dataJsonHotel.toString());
  return jsonParse;
}

/**
  retorna los hoteles que posean el nombre pasado por parametro
**/
function buscarPorNombre(hotelName) {
  var dataJsonHotel = dataHoteles();
  var jsonParse = JSON.parse(dataJsonHotel.toString());
  var result = jsonQuery("[name="+hotelName+"]", {data: jsonParse}).value;
  return result;
}

module.exports.listarHoteles = listarHoteles; // se publica el metodo de lectura a traves de controller
module.exports.buscarPorNombre = buscarPorNombre; // se publica el metodo de buscar por nombre

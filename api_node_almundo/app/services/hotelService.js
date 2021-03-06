/**
  hotelService.js,
  capa de servicios en donde se realizan las peticiones http - rest,
  realiza la comunicacion con el controller (logica de negocio) y asi mismo con el
  model, capa de datos.
*/

var hotelController = require("../controller/hotelsController");

var listarHoteles = hotelController.listarHoteles;
var listarHotelPorNombre = hotelController.buscarPorNombre;
var listarHotelPorEstrella = hotelController.buscarPorEstrellas;


function getHotels(req, res) {
  var hotels = listarHoteles();
  if (hotels == null || hotels.length <= 0) {
    return res.status(404).send("No hay Datos");
  }
  return res.status(200).jsonp(hotels);
}

function getHotelPorNombre(req, res) {
  console.log(req.params.nombre)
  var hotel = listarHotelPorNombre(req.params.nombre);
  if (hotel == null || hotel.length <= 0) {
    return res.status(404).send("No hay Datos");
  }
  return res.status(200).jsonp(hotel);
}

function getHotelPorEstrella(req, res) {
  var hotels = listarHotelPorEstrella(req.body);
  if (hotels == null || hotels.length <= 0) {
    return res.status(404).send("No hay Datos");
  }
  return res.status(200).jsonp(hotels);
}

module.exports.restGetHotel = getHotels;
module.exports.restGetHotelPorNombre = getHotelPorNombre;
module.exports.restGetHotelPorEstrella = getHotelPorEstrella;

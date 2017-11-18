/**
  hotelService.js,
  capa de servicios en donde se realizan las peticiones http - rest,
  realiza la comunicacion con el controller (logica de negocio) y asi mismo con el
  model, capa de datos.
*/

var hotelController = require("../controller/hotelsController");

var listarHoteles = hotelController.listarHoteles;
var listarHotelPorNombre = hotelController.buscarPorNombre;


function getHotels(req, res){
  var hotels = listarHoteles();
  if(hotels == null || hotels.length <= 0){
      res.send(404, "No hay datos");
  }
  res.status(200).jsonp(hotels);
}

function getHotelPorNombre(req, res) {
  var hotel = listarHotelPorNombre(req.params.nombre);
  if(hotel == null || hotel.length <= 0) {
      res.send(404, "No hay datos");
  }
  res.status(200).jsonp(hotel);
}

module.exports.restGetHotel = getHotels;
module.exports.restGetHotelPorNombre = getHotelPorNombre;

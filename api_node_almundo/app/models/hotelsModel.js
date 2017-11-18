/**
  hotelesModel.js, model en donde se realiza la comunicacion y lectura con el archivo data.json y
  se retornan todos los datos.
*/

var fs = require ("fs"); // require del modulo de node para lectura de archivos

function findAll(){
  var jsonHotels = fs.readFileSync("./data/data.json");
  return jsonHotels;
}

module.exports.findAll = findAll; // se exporta el metodo dentro de exports.

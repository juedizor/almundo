var express = require("express"),
    hotelService = require("./services/hotelService.js");


var getHoteles = hotelService.restGetHotel;
var getHotelesPorNombre = hotelService.restGetHotelPorNombre;

var app = express();

app.use(express.static("./data/assets")); // archivos staticos para servirlos al cliente

// API routes
var apiRoutes = express.Router();
apiRoutes.route("/hoteles")
  .get(getHoteles);

apiRoutes.route("/hoteles/:nombre")
  .get(getHotelesPorNombre);

app.use('/api', apiRoutes);

// Start server
app.listen(8080);

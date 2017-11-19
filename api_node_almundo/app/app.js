var express = require("express"),
    hotelService = require("./services/hotelService.js"),
    config = require('./config/config.js').get(process.env.NODE_ENV);


var getHoteles = hotelService.restGetHotel;
var getHotelesPorNombre = hotelService.restGetHotelPorNombre;

var app = express();
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

app.use(express.static("./data/assets")); // archivos staticos para servirlos al cliente

// API routes
var apiRoutes = express.Router();
apiRoutes.route("/hoteles")
  .get(getHoteles);

apiRoutes.route("/hoteles/:nombre")
  .get(getHotelesPorNombre);

app.use('/api', apiRoutes);

// Start server
app.listen(config.port);

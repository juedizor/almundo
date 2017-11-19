(function() {
  'use strict';

  /**
   * almundo Module
   *
   * Description
   */
  angular.module('almundo', [
    'ngRoute',
    'almundo.hotelControler',
    'angularSpinner'
  ]);

  // generacion de una constante para la direccion http de los servicios
  angular.module('almundo').constant("urlRest", "http://localhost:3000/api");

  // url de las imagenes estaticas ofrecidas pr el server
  angular.module('almundo').constant("image_static", "http://localhost:3000/images/hotels/");

  angular.module('almundo').constant("icons", "http://localhost:3000/icons/amenities/");

  angular.module('almundo').constant("star", "http://localhost:3000/icons/filters/");



})();

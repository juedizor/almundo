(function() {
  'use strict';

  /**
   * almundo Module
   *
   * Description
   */
  angular.module('almundo', [
    'ngRoute',
    'almundo.hotelControler'
  ]);

  // generacion de una constante para la direccion http de los servicios
  angular.module('almundo').constant("urlRest", "http://localhost:3000/api");

})();

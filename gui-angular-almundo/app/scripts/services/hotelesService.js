(function() {
  'use strict'

  angular.module('almundo.hotelService', ['ngResource']);

  function getHoteles($resource, urlRest) {
    return $resource(urlRest + "/hoteles", {}, {
      getHotelByNombre: {
        url: urlRest + "/hoteles/:nombre",
        method: 'GET',
        params: {
          nombre: "@nombre"
        },
        isArray: true
      }
    });
  }

  angular.module('almundo.hotelService').factory('hotelesService', getHoteles);

})();

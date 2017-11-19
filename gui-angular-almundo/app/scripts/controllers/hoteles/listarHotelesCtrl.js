(function() {
  'use strict'


  angular.module('almundo.hotelControler', ['almundo.hotelService']);

  /**
  Realiza la consulta de todos los hoteles a traves del servicio
  hotelesService, el cual se esta inyectanto a partir de almundo.hotelService
  */
  function getHoteles(hotelesService, image_static, icons, star) {
    var self = this;
    self.hoteles = [];
    self.rutaImagenes = image_static;
    self.amenities = icons;
    self.stars = star;
    self.estrellas = {};
    self.repeat = function(number) {
      self.estrellas = {};
      for (var i = 1; i <= number; i++) {
        self.estrellas[i] = "star";
      }
      return true;
    }

    console.log('antes de ejecutar aqui se muestra un gif');
    hotelesService.query().$promise.then(
      function(data) {
        console.log('entre aqui, tengo resultados, quito el gif de carga')
        console.log(data)
        self.hoteles = data;

      },
      function(err) {
        console.log('genere error no hay conexion')
        console.log(err);
      }
    )
  }
  angular.module('almundo.hotelControler').controller('mainCtrl', getHoteles);


})();

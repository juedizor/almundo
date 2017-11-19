(function() {
  'use strict'


  angular.module('almundo.hotelControler', ['almundo.hotelService']);

  /**
  Realiza la consulta de todos los hoteles a traves del servicio
  hotelesService, el cual se esta inyectanto a partir de almundo.hotelService
  */
  function gestionarHoteles(hotelesService, image_static, icons, star) {
    var self = this;
    self.hoteles = [];
    self.rutaImagenes = image_static;
    self.amenities = icons;
    self.stars = star;
    self.estrellas = {};
    self.oneStar = false;
    self.twoStar = false;
    self.threeStar = false;
    self.fourStar =false;
    self.fiveStar = false;
    self.allStar = false;
    self.showSpinner = false;


    self.repeat = function(number) {
      self.estrellas = {};
      for (var i = 1; i <= number; i++) {
        self.estrellas[i] = "star";
      }
      return true;
    }


    hotelesService.query().$promise.then(
      function(data) {
        self.hoteles = data;
      },
      function(err) {
        self.hoteles = {};
      }
    )

    self.buscarPorEstrellas = function(array) {
      // aqui realiza la consulta al servicio con las estrellas seleccionadas
      hotelesService.getHotelByEstrellas(array).$promise.then(
        function(data) {
          self.hoteles = data;
          self.showSpinner = false;
        },
        function(err) {
          self.showSpinner = false;
        }
      )
    }


    self.starBusqueda = [];

    self.clickStarAll = function() {
      self.showSpinner = true;
      self.starBusqueda = [];
      if (self.allStar == true) {
        self.starBusqueda.push(1);
        self.starBusqueda.push(2);
        self.starBusqueda.push(3);
        self.starBusqueda.push(4);
        self.starBusqueda.push(5);
        self.oneStar = false;
        self.twoStar = false;
        self.threeStar = false;
        self.fourStar = false;
        self.fiveStar = false;
        self.buscarPorEstrellas(self.starBusqueda);
      }

    }

    self.clickStar = function() {
      self.showSpinner = true;
      self.starBusqueda = [];

      self.allStar = false;
      if (self.oneStar == true) {
        self.starBusqueda.push(1);
      }
      if (self.twoStar == true) {
        self.starBusqueda.push(2);
      }
      if (self.threeStar == true) {
        self.starBusqueda.push(3);
      }
      if (self.fourStar == true) {
        self.starBusqueda.push(4);
      }
      if (self.fiveStar == true) {
        self.starBusqueda.push(5);
      }
      self.buscarPorEstrellas(self.starBusqueda);

    }



    self.nombreHotel = "";
    self.buscarPorNombre = function() {
      if (self.nombreHotel == null || self.nombreHotel.length <= 0) {
        return;
      }
      hotelesService.getHotelByNombre({
        nombre: self.nombreHotel
      }).$promise.then(function(data) {
          self.hoteles = data;
        },
        function(err) {
          self.hoteles = {};
        })
    }

    self.validarVacio = function() {
      if (self.nombreHotel == null || self.nombreHotel.length <= 0) {
        hotelesService.query().$promise.then(
          function(data) {
            self.hoteles = data;
          },
          function(err) {
            self.hoteles = {};
          }
        )
      }
    }




  }
  angular.module('almundo.hotelControler').controller('mainCtrl', gestionarHoteles);


})();

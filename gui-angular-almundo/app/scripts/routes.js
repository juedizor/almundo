(function() {
  'use strict';

  function config($routeProvider) {
    $routeProvider.when('/', {
      templateUrl: 'views/hoteles/hoteles.html',
      controller: 'mainCtrl',
      controllerAs: 'mainCtrl'
    }).when('/hoteles/:nombre', {
      templateUrl: 'views/hoteles/hoteles.html',
      controller: 'busquedaNombreCtrl',
      controllerAs: 'bNombreCtrl'
    }).otherwise({
      redirectTo: '/'
    })
  }

  angular.module('almundo').config(config);

})();

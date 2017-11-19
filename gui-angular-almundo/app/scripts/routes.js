(function() {
  'use strict';

  function config($routeProvider) {
    $routeProvider.when('/', {
      templateUrl: 'views/hoteles/hoteles.html',
      controller: 'mainCtrl',
      controllerAs: 'mainCtrl'
    }).otherwise({
      redirectTo: '/'
    })
  }

  angular.module('almundo').config(config);

})();

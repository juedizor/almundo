(function(){
	'use strict';

	function config($routeProvider) {
		$routeProvider.when('/hoteles', {
			templateUrl: 'views/hoteles/hoteles.html',
			controller: 'hotelesController',
			controllerAs: 'hotelCtrl'
		}).otherwise({ redirectTo: '/' })
	}

	angular.module('almundo').config(config);

})();

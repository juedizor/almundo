(function(){
	'use strict';

	/**
	* kangaroo.brandService Module
	*
	* Description
	*/
	angular.module('kangaroo.brandService', ['ngResource']);

	function getBrand($resource, BaseUrl){
		return $resource(BaseUrl + "/brand", {});
	}

	angular.module('kangaroo.brandService')
	.constant('BaseUrl', 'http://localhost:8090')
	.factory('Brand', getBrand);
})();
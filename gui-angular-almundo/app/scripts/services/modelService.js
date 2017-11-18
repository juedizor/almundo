(function(){
	'use strict';


	/**
	*  Module
	*
	* Description
	*/
	angular.module('kangaroo.modelService', ['ngResource']);

	function getModels($resource, BaseUrl){
		return $resource(BaseUrl + "/brand/model", {brand: '@id'});	
	}

	angular.module('kangaroo.modelService')
	.constant('BaseUrl', 'http://localhost:8090')
	.factory('Model', getModels);


})();
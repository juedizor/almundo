(function(){
	'use strict';


	/**
	*  Module
	*
	* Description
	*/
	angular.module('kangaroo.compareService', ['ngResource']);


	function getCompareSecures($resource, BaseUrl){
		return $resource(BaseUrl + "/compare", {});
	}

	angular.module('kangaroo.compareService')
	.constant('BaseUrl', 'http://localhost:8090')
	.factory('Compare', getCompareSecures);


})();
(function(){
	'use strict';

	/**
	* kangaroo.referenceService Module
	*
	* Description
	*/
	angular.module('kangaroo.referenceService', ['ngResource']);

	function getReferences($resource, BaseUrl){
		return $resource(BaseUrl + "/brand/model/references", {}, 
			{
				getReferences :{
					method: 'GET', 
					url: BaseUrl + "/brand/model/references", 
					params: {brand: "@brand", model: "@model"}, 
					isArray: true
				}

			});
	}

	angular.module('kangaroo.referenceService')
	.constant('BaseUrl', 'http://localhost:8090')
	.factory('References', getReferences);

})();
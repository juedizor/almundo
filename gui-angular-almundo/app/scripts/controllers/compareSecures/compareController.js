(function(){
	'use strict';


	/**
	*  Module
	*
	* Description
	*/
	angular.module('kangaroo.compareController', ['kangaroo.compareService']);

	function getCompareSecures(Compare, $rootScope){
		var self = this;
		this.dataCompare = {};
		$rootScope.$on("dataCompare", function(event, data){
			console.log(data.success.leadId)
	    	self.dataCompare = data.success;
	    	console.log(self.dataCompare.quotes)
	    });
	}

	angular.module('kangaroo.compareController').controller('compareCtrl', getCompareSecures);



})();
(function(){
	'use strict';

	/**
	*  Module
	*
	* Description
	*/
	angular.module('kangaroo.mainController', [
		'kangaroo.brandService', 
		'kangaroo.modelService', 
		'kangaroo.referenceService', 
		'kangaroo.compareService'
		]);

	function getBrand(Brand, Model, References, $location, Compare, $rootScope){
		this.brand = {};
		this.models = {};
		this.references = {};
		this.brandSelected = {};
		this.modelSelected = {};
		this.referencesSelected = {};
		var self = this;
		Brand.query().$promise.then(
			function(data){
				self.brand = data;
			}, 
			function(error){
				console.log("error");
		});


		this.brandOnChange = function(){
			if(self.brandSelected != null){
				Model.query({brand: self.brandSelected.nombre}).$promise.then(
					function(success){
						self.models = success;
					}, 
					function(error){
						self.models = {};
					}
					);
			}else{
				self.models = {};
			}
		}

		this.modelOnChange = function(){
			if(self.modelSelected != null){
				References.getReferences({brand: self.brandSelected.nombre, 
					model: self.modelSelected.value}).$promise.then(
						function(success){
							self.references = success;
						}, 
						function(error){
							self.references = {};
						}
					)
			}else{
				self.references = {};
			}
		}

		this.compare = function(){
			this.compareData = {};
			var self = this;
			Compare.get().$promise.then(
				function(success) {
					self.compareData = success;
					$rootScope.$emit('dataCompare', {success});

				}, 
				function(error){
					console.log(error);
				});	
			
			$location.url("/compare");
		}
		
	}



	angular.module('kangaroo.mainController').controller('mainCtrl', getBrand);

})();
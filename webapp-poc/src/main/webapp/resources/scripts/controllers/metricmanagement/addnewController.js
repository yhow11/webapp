'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:AddNewController
 * @description # AddNewController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'AddNewController',
		function($rootScope, $scope, $q,  $timeout, $stateParams, metricService, metricTypeService, keymanagementService) {
			
			
			
			$scope.metrics = {
					data: {
						name: "",
						types: [],
						keys: []
					},
					typefield: {
						searchText: null,
						search: function(query) {
							var deferred = $q.defer();
							metricTypeService.getAll().then(function(data){
								if(data.data){
									deferred.resolve( data.data.data );
								}
							});
							return deferred.promise;
			            },
						onAddChip: function($chip, keys){
							if(keys.length > 0) {
								$scope.metrics.keyfield.validateKey(keys[0], $chip);
							}
						}
					},
					keyfield: {
						searchText: null,
						search: function(query) {
							var deferred = $q.defer();
							keymanagementService.getAll("%25"+query+"%25","","").then(function(data){
								if(data.data){
									deferred.resolve( data.data.data );
								}
							});
							return deferred.promise;
			            },
			            getValueStr: function(chip){
							var values = [];
							for(var index in chip.values){
								values.push(chip.values[index].value);
							}
							return values.join();
						},
						isNumeric: function (n) {
							  return !isNaN(parseFloat(n)) && isFinite(n);
						},
						isValid: true,
						validateKey: function(key, type){
							if(type != null && type.type == 'KEYSUM'){
								for(var index in key.values){
									if(!this.isNumeric(key.values[index].value)){
										this.isValid = false;
									}
								}
			            	} else {
			            		this.isValid = true;
			            	}
						},
						onAddChip: function(chip, types){
							if(types.length > 0) {
								$scope.metrics.keyfield.validateKey(chip, types[0]);
							}
						}
						
					},
					init: function(){
						$scope.metrics.data = {
							types: [],
							keys: []
						};
						$scope.metrics.typefield.searchText = null;
						$scope.metrics.keyfield.searchText = null;
					},
					save: function(form){
						metricService.save($scope.metrics.data).then(function(data){
							if(data.data){
								$scope.metrics.init();
								$rootScope.toast("Success!", "md-primary");
								form.$setPristine();
								form.$setUntouched();
								form.$error = {};
								
							}
						});
					}
			};
			$scope.isOnEdit = false;
			if($stateParams.id != null){
				metricService.get($stateParams.id).then(function(data){
					if(data.data){
						$scope.metrics.data = data.data.data[0];
					}
				});
				$scope.isOnEdit = true;
			};
		});

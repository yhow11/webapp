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
					},
					typefield: {
						searchText: null,
						items: [],
						search: function(query) {
							var deferred = $q.defer();
							metricTypeService.getAll().then(function(data){
								if(data.data){
									deferred.resolve( data.data.data );
								}
							});
							return deferred.promise;
			            }
					},
					keyfield: {
						searchText: null,
						items: [],
						search: function(query) {
							var deferred = $q.defer();
							keymanagementService.getAll("%25"+query+"%25","","").then(function(data){
								if(data.data){
									deferred.resolve( data.data.data );
								}
							});
							return deferred.promise;
			            }
					},
					init: function(){
						$scope.metrics.data = {};
						$scope.metrics.typefield.searchText = null;
						$scope.metrics.keyfield.searchText = null;
					},
					save: function(form){
						if($scope.metrics.data.type){
							$scope.metrics.data.type = $scope.metrics.data.type.type;
						}
						if($scope.metrics.data.key){
							$scope.metrics.data.key = $scope.metrics.data.key.key;
						}
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
			if($stateParams.param != null){
				$scope.metrics.data = {
						id: $stateParams.param.id,
						name: $stateParams.param.name,
						type: { type: $stateParams.param.type },
						key: { key: $stateParams.param.key }
				};
				$scope.isOnEdit = true;
			}
		});

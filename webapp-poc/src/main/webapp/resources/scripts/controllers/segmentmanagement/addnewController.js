'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:AddNewController
 * @description # AddNewController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'AddNewController',
		function($rootScope, $scope, $q,  $timeout, $stateParams, metricService, segmentConditionService,segmentService) {
			
			
			$scope.segments = {
					data: {
						name: "",
						metrics: [],
						metrictype: "",
						condition: ""
					},
					metricnamesfield: {
						searchText: null,
						search: function(query) {
							var deferred = $q.defer();
							metricService.search(query).then(function(data){
								if(data.data){
									deferred.resolve( data.data.data );
								}
							});
							return deferred.promise;
			            },
						onAddChip: function($chip){
							$scope.segments.data.metrictype = $scope.segments.data.metrics[0].types[0].type;
							segmentConditionService.getAll($scope.segments.data.metrictype).then(function(data){
								if(data.data){
									$scope.segments.conditionsfield.filters = data.data.data;
								}
							});
						}
					},
					conditionsfield: {
						conditions: []
					},
					init: function(){
						$scope.segments.data = {
								metrics: []
						};
					},
					save: function(form){
						segmentService.save($scope.segments.data).then(function(data){
								if(data.data){
								$scope.segments.init();
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
				segmentService.get($stateParams.id).then(function(data){
					if(data.data){
						$scope.segments.data = data.data.data[0];
						$scope.segments.data.metrictype = $scope.segments.data.metrics[0].types[0].type;
						segmentConditionService.getAll($scope.segments.data.metrictype).then(function(data){
							if(data.data){
								$scope.segments.conditionsfield.filters = data.data.data;
							}
						});
					}
				});
				$scope.isOnEdit = true;
			};
		});

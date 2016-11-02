'use strict';
/**
 * @ngdoc function
 * @name incToolApp.controller:WorkerProfileController
 * @description # WorkerProfileController Controller of the incToolApp
 */
angular.module('incToolApp').controller(
		'WorkerProfileController',
		function($rootScope, $scope, $stateParams, $q, $timeout,  workerService) {
		
			$scope.save = function() {
				workerService.save($scope.worker).then(function(data){
					 if(data.status == 200) {
						 $scope.worker = {};
						 $scope.workerForm.$setPristine();
						 $scope.workerForm.$setUntouched();
						 $scope.workerForm.$error = {};
						 $rootScope.toast("Success!");
					 }
					 
				});
			}
			
			$scope.isOnEdit = false;
			if($stateParams.id != null){
				workerService.get($stateParams.id).then(function(data){
					if(data.data){
						$scope.worker = data.data;
					}
				});
				$scope.isOnEdit = true;
			};
		});

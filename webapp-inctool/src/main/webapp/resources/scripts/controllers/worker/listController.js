'use strict';
/**
 * @ngdoc function
 * @name incToolApp.controller:WorkerViewController
 * @description # WorkerViewController Controller of the incToolApp
 */
angular.module('incToolApp').controller(
		'WorkerViewController',
		function($rootScope, $scope, $stateParams, $q, $timeout,  workerService) {
		
			$scope.save = function() {
				workerService.save($scope.worker).then(function(data){
					 if(data.status == 200) {
						 $scope.worker = {};
						 $scope.workerForm.$setPristine();
						 $scope.workerForm.$setUntouched();
						 $scope.workerForm.$error = {};
					 }
					 
				});
			}
		});

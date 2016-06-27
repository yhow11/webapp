'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:MonitoringController
 * @description # MonitoringController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'MonitoringController',
		function($rootScope, $scope, eventService) {
			
			$scope.selected = [];
			 eventService.getAll().then(function(data){
				if(data.data.status) {
					$scope.events = data.data.data;
				}
			});
			
			$rootScope.$on("stomReceiversConnected", function(event, next,
					current) {
				$rootScope.stompClient.subscribe('/event/notifyReceivers', function(
						data) {
					$scope.events = JSON.parse(data.body).concat($scope.events);
					$scope.$apply();
				});
			});
			
		});
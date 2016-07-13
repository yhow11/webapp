'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:MonitoringController
 * @description # MonitoringController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'MonitoringController',
		function($rootScope, $scope, eventService, Reddit) {
			$scope.reddit = new Reddit();
			
			$rootScope.$on("stomReceiversConnected", function(event, next,
					current) {
				$rootScope.stompReceivers.subscribe('/event/notifyReceivers', function(
						data) {
					var response =  JSON.parse(data.body);
					if(response && response.type == "VisitorLogModel"){
						$scope.reddit.items = JSON.parse(response.data).concat($scope.reddit.items);
						$scope.$apply();
					}
					
				});
			});
			
		});
'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ReportsController
 * @description # ReportsController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ReportsController',
		function($rootScope, $scope, eventService) {
			eventService.getAll().then(function(data){
		 		if(data.status == 200) {
		 			$scope.data = data.data.data;
		 		}
				console.log(data);
			});
			$scope.$on("stomReceiversConnected", function(evt,data){ 
				 $rootScope.stompReceivers.subscribe('/event/notifyReceivers', function(events){
				     	console.log("notify");  
				     	if(events) {
				     		$scope.$apply(function() {
				     			$scope.data = JSON.parse(events.body);
			     		    });
			     			
			     		}
				     });
			});
			
			
		});

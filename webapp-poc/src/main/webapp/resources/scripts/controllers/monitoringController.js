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
			
			$rootScope.$on("notifyReceivers", function(event, data) {
				if(data.type == "VisitorLogModel"){
					var items = data.data;
					for (var i = 0; i < items.length; i++) {
						 items[i].timeStamp = moment(new Date(Number(items[i].timeStamp))).format("LLL");
				    }
					$scope.reddit.items = items.concat($scope.reddit.items);
					$scope.$apply();
				}
			});
			
		});
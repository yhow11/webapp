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
					var response = JSON.parse(JSON.stringify(data.body));
					if(typeof response.type !== "undefined"){
						$scope.reddit.items = JSON.parse(response.data).concat($scope.reddit.items);
						$scope.$apply();
					} else {
						if(JSON.parse(response).type == "VisitorLogModel"){
							var items = JSON.parse(response).data;
							for (var i = 0; i < items.length; i++) {
								 items[i].timeStamp = moment(new Date(Number(items[i].timeStamp))).format("LLL");
						    }
							$scope.reddit.items = items.concat($scope.reddit.items);
							$scope.$apply();
						}
						
					}
					
				});
			});
			
		});
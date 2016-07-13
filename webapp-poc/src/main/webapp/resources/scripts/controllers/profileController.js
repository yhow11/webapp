'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ProfileController
 * @description # ProfileController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ProfileController',
		function($rootScope, $scope, eventService, Reddit) {
			$scope.reddit = new Reddit();
			eventService.getAnonymousUser(FingerPrint.getData()).then(function(data){
				if(data.data.status) {
					$scope.fingerPrintData = data.data.data[0];
				}
			});
			
			
			$rootScope.$on("stomReceiversConnected", function(event, next,
					current) {
				$rootScope.stompReceivers.subscribe('/event/notifyReceivers', function(
						data) {
					var response = JSON.parse(JSON.stringify(data.body));
					if(typeof response.type !== "undefined"){
						$scope.reddit.items = JSON.parse(response.data).concat($scope.reddit.items);
						$scope.$apply();
					} else {
						if(JSON.parse(response).type == "WebEventModel"){
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

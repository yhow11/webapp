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
			$rootScope.$on("notifyReceivers", function(event, data) {
				if(data.type == "WebEventModel"){
					var items = data.data;
					for (var i = 0; i < items.length; i++) {
						 items[i].timeStamp = moment(new Date(Number(items[i].timeStamp))).format("LLL");
				    }
					$scope.reddit.items = items.concat($scope.reddit.items);
					$scope.$apply();
				}
			});
		});

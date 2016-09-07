'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:LogsController
 * @description # LogsController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'LogsController',
		function($rootScope, $scope, LogsInfiniteScroll) {
			$scope.logsInfiniteScroll = new LogsInfiniteScroll();
			
			$rootScope.$on("notifyReceivers", function(event, data) {
				if(data.data.type == "VisitorLogModel"){
					var items = data.data.data;
					for (var i = 0; i < items.length; i++) {
						 items[i].timeStamp = moment(new Date(Number(items[i].timeStamp))).format("LLL");
				    }
					$scope.reddit.items = items.concat($scope.reddit.items);
					$scope.$apply();
				}
			});
			
		});
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
			
			
			
		});

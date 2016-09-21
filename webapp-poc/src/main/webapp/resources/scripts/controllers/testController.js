'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:TestController
 * @description # TestController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller('TestController',
		function($rootScope, $scope, $timeout, eventService) {
	$scope.init = function(){
		var fpData = FingerPrint.getData();
		TIMERUTIL.getTime(baseURL).then(function(data){
			fpData.timestamp = data;
			$scope.data = fpData;
			$timeout(function(){
				$scope.$apply();
			});
		}); 
		
		
		
	};
	
	if(typeof FingerPrint !== 'undefined'){
		$scope.init();
	}
	
	
	EventDispatcher.register('on-fingerprint-loaded', function(param){
		$scope.init();
	});
	
});
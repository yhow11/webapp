'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:TestController
 * @description # TestController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller('TestController',
		function($rootScope, $scope, eventService) {
	var data = FingerPrint.getData();
	$scope.data = data;
});
'use strict';
/**
 * @ngdoc function
 * @name ispMintApp.controller:LoginController
 * @description
 * # LoginController
 * Controller of the ispMintApp
 */
angular.module('ispMintApp').controller('LoginController', function ( $http, $interval, $state, $rootScope, $scope, $timeout, authenticationService) {
	$scope.login = function(form){
	
		authenticationService.login(form.username, form.password).success(function(response){
			if(response != null && response.status) {
				authenticationService.setCredential(form.username, form.password, response.data);
				$state.go('home');
			} else {
				$rootScope.displayError("Invalid credentials!");
			}
			
		});
	}
	
});
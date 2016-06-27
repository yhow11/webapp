'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp').controller('LoginCtrl', function ($state, $scope, $timeout, authenticationService) {
	$scope.login = function(form){
		authenticationService.login(form.username, form.password).success(function(response){
			if(response != null && response.status=="SUCCESS") {
				authenticationService.setCredential(form.username, form.password, response.data);
				$state.go('dashboard.home');
			} else {
				$state.go('login');
			}
			
		});
	}
	
});
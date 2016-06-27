'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('DashboardCtrl', function($state,$scope,$rootScope, $position, authenticationService) {
	  
	  $rootScope.logout = function(){
		  authenticationService.clearCredential();
		  authenticationService.logout().success(function(response){
			  if(response.status == "SUCCESS") {
				  $state.go('login');    
			  }
		  }); 
	  }
  });

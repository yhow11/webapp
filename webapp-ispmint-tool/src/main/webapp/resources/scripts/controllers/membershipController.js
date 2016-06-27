'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MembershipController
 * @description # MembershipController Controller of the ispMintApp
 */
angular.module('ispMintApp').controller(
		'MembershipController',
		function($scope, $stateParams, memberService) {
			if($stateParams.id == null) {
				memberService.getTemplate().then(function(data){
					 if(data.status == 200) {
						 if(data.data.status) {
							 $scope.member = data.data.data[0];
						 }
						 
					 }
					 
				});
			} else {
				memberService.get($stateParams.id).then(function(data){
					 if(data.status == 200) {
						 if(data.data.status) {
							 $scope.member = data.data.data[0];
						 }
						 
					 }
					 
				});
			}
			
		});

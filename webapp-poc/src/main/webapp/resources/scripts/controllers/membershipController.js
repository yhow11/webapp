'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MembershipController
 * @description # MembershipController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'MembershipController',
		function($rootScope, $scope, $stateParams, memberService) {
			
			$scope.labels = ["Attended", "Absent/s", "Left"];
			$scope.datas = [];
			$scope.showLabel = true;  
			memberService.get($stateParams.id).then(function(data){
				 if(data.status == 200) {
					 if(data.data.status) {
						 $scope.member = data.data.data[0];
						 $scope.datas = [Number(data.data.data[0].present), Number(data.data.data[0].absent), 48-(Number(data.data.data[0].present)+Number(data.data.data[0].absent))];
					 }
					 
				 }
				 
			});
			
			$scope.save = function() {
				memberService.save($scope.member).then(function(data){
					 if(data.status == 200) {
						 if(data.data.status) {
							 $rootScope.goTo("encoding");
						 }
						 
					 }
					 
				});
			}
			$scope.change = function(date, index) {
				$scope.member.r310[index] = date;
				$scope.member;
			}
		});

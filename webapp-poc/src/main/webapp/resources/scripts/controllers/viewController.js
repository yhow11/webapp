'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ViewController
 * @description # ViewController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ViewController',
		function($rootScope, $scope, $state, KeymanagementInfiniteScroll, keymanagementService) {
			$scope.keymanagementInfiniteScroll = new KeymanagementInfiniteScroll();
			$scope.selected = [];
			$scope.items = [{
				key: "Category",
				values: "Wow, Wew, Wiw",
				options: ["edit", "delete"]
			}]
			$scope.remove = function(item){
				keymanagementService.remove(item.id).then(function(data){
					if(data.data.status){
						$rootScope.toast("Success!", "md-primary");
						$scope.keymanagementInfiniteScroll.refreshPage();
					}
				});
			};
		});
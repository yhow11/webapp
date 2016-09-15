'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ViewController
 * @description # ViewController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ViewController',
		function($rootScope, $scope, $state, MetricInfiniteScroll, metricService) {
			$scope.metricInfiniteScroll = new MetricInfiniteScroll();
			$scope.selected = [];
			$scope.remove = function(item){
				metricService.remove(item).then(function(data){
					if(data.data.status){
						$rootScope.toast("Success!", "md-primary");
						$scope.metricInfiniteScroll.refreshPage();
					}
				});
			};
		});
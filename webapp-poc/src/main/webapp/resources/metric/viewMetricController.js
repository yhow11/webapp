'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ViewMetricController
 * @description # ViewMetricController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ViewMetricController',
		function($rootScope, $scope, $state, metricService) {
			$scope.selected = [];
			$scope.remove = function(item){
				metricService.remove(item).then(function(data){
					if(data.data.status){
						$rootScope.toast("Success!", "md-primary");
						$scope.metricTable.refresh();
					}
				});
			};
			$scope.metricTable = {
					searchValue : null,
					data : {},
					refresh : function() {
						metricService
								.getPage($scope.metricTable.searchValue,
										$scope.metricTable.query.page,
										$scope.metricTable.query.limit)
								.then(
										function(data) {
											if (data.data.status) {
												$scope.metricTable.data.items = data.data.data;
												$scope.metricTable.data.count = data.data.count;
											}
										});
					},
					items : [],
					query : {
						order : 'name',
						limit : 5,
						page : 1
					},
					onPaginate : function(page, limit) {
						$scope.metricTable.filter();
					},
					onReorder : function(order) {

					},
					selected : [],
					onSelect : function(item) {

					},
					onDeselect : function(item) {

					},
					limitOptions : [
							5,
							10,
							15,
							{
								label : 'All',
								value : function() {
									return $scope.metricTable.data.count ? $scope.metricTable.data.count
											: 0;
								}
							} ],
					options : {
						rowSelection : true,
						multiSelect : true,
						autoSelect : true,
						decapitate : false,
						largeEditDialog : false,
						boundaryLinks : false,
						limitSelect : true,
						pageSelect : true
					}
				};
				$scope.$watch('metricTable.searchValue', function() {
					$scope.metricTable.refresh();
			    });
		});
'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ViewKeyController
 * @description # ViewKeyController Controller of the fingerPrintApp
 */
angular
		.module('fingerPrintApp')
		.controller(
				'ViewKeyController',
				function($rootScope, $scope, $state, keyService) {
					$scope.remove = function(item) {
						keyService.remove(item.key).then(
								function(data) {
									if (data.data.status) {
										$rootScope.toast("Success!",
												"md-primary");
										$scope.keyTable.refresh();
									}
								});
					};
					$scope.keyTable = {
						searchValue : null,
						data : {},
						refresh : function() {
							keyService
									.getPage($scope.keyTable.searchValue,
											$scope.keyTable.query.page,
											$scope.keyTable.query.limit)
									.then(
											function(data) {
												if (data.data.status) {
													$scope.keyTable.data.items = data.data.data;
													$scope.keyTable.data.count = data.data.count;
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
							$scope.keyTable.filter();
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
										return $scope.keyTable.data.count ? $scope.keyTable.data.count
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
						},
						getValueStr : function(item) {
							var values = [];
							for ( var index in item.values) {
								values.push(item.values[index].value);
							}
							return values.join();
						}
					};
					$scope.$watch('keyTable.searchValue', function() {
						$scope.keyTable.refresh();
				    });
					
				});
'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:URLImportController
 * @description # URLImportController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'URLImportController',
		function($rootScope, $scope, $q,  $timeout, $mdDialog, $mdMedia, URLImportService) {
			$scope.urlTable = {
					save: function(items){
						URLImportService.save(items).then(function(data){
							if(data.data.status){
								$scope.sitemapURLForm.$setPristine();
								$scope.sitemapURLForm.$setUntouched();
								$scope.urlTable.sitemapURL = '';
								$scope.urlTable.items = [];
								$rootScope.toast("Success!", "md-primary");
							}
						});
					},
					sitemapURL: null,
					get: function(sitemapURLForm){
						if(sitemapURLForm.sitemapURL.$valid){
							URLImportService.getAll($scope.urlTable.sitemapURL).then(function(data){
								if(data.data.status){
									$scope.urlTable.items = data.data.data;
									
								}
							});
						}
					},
					items: [],
					query: {
							    order: 'name',
							    limit: 5,
							    page: 1
				    },
					options: {
						    rowSelection: false,
						    multiSelect: false,
						    autoSelect: true,
						    decapitate: false,
						    largeEditDialog: false,
						    boundaryLinks: false,
						    limitSelect: true,
						    pageSelect: true
						  }	
			}

		});

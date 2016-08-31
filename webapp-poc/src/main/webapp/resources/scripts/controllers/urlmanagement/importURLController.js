'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ImportURLController
 * @description # ImportURLController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ImportURLController',
		function($rootScope, $scope, $q,  $timeout, $mdDialog, $mdMedia, importURLService) {
			$scope.urlTable = {
					save: function(items){
						importURLService.save(items).then(function(data){
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
							importURLService.getAll($scope.urlTable.sitemapURL).then(function(data){
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

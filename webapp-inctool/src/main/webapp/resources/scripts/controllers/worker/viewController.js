'use strict';
/**
 * @ngdoc function
 * @name incToolApp.controller:WorkerViewController
 * @description # WorkerViewController Controller of the incToolApp
 */
angular.module('incToolApp').controller(
		'WorkerViewController',
		function($rootScope, $scope, $stateParams, $q, $timeout, $location,  workerService) {
			
			
			
			$scope.workerTable = {
					searchValue: null,
					items: [],
					totalCount: 0,
					search: function(){
						workerService.getAll($scope.workerTable.searchValue, $scope.workerTable.query.page, $scope.workerTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.workerTable.items = data.data.data;
								$scope.workerTable.totalCount = data.data.count;
							}
						});
					},
					count: function(){
						workerService.count($scope.workerTable.searchValue).then(function(data){
							if(data.data){
								$scope.workerTable.totalCount = data.data.count;
							}
						});
					},
					remove: function(id){
						workerService.remove(id).then(function(data){
							if(data.data.status){
								$scope.workerTable.search();
								$rootScope.toast("Success!");
							}
						});
					},
					query: {
							    order: 'name',
							    limit: 5,
							    page: 1
				    },
				    onPaginate: function(page, limit) {
				    	this.search();
					},
				    onReorder: function(order) {

					},
					selected: [],
					onSelect: function(item){
						
					},
					onDeselect: function(item){
						
					},
					limitOptions: [5, 10, 15, {
					    label: 'All',
					    value: function () {
					      return $scope.workerTable.totalCount ? $scope.workerTable.totalCount : 0;
					    }
				     }],
					options: {
						    rowSelection: true,
						    multiSelect: true,
						    autoSelect: true,
						    decapitate: false,
						    largeEditDialog: false,
						    boundaryLinks: false,
						    limitSelect: true,
						    pageSelect: true
						  }	,
					action: function(option, item){
						if(option.value == "EDIT"){
							 $location.path("/worker/worker/profile/"+item.id);
						} else {
							$scope.workerTable.remove(item.id);
						}
					}
			};
			$scope.workerTable.search();
		});

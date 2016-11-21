'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ViewController
 * @description # ViewController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ViewController',
		function($rootScope, $scope, $q,  $timeout, segmentService) {
			segmentService.count("").then(function(data){
				$scope.totalCount = data.data.count;
			});
			
			$scope.segmentTable = {
					searchValue: null,
					filter: function(){
						segmentService.getAll('', $scope.segmentTable.query.page, $scope.segmentTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.segmentTable.items = data.data.data;
							}
						});
					},
					items: [],
					query: {
							    order: 'name',
							    limit: 5,
							    page: 1
				    },
				    onPaginate: function(page, limit) {
				    	$scope.segmentTable.filter();
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
					      return $scope.totalCount ? $scope.totalCount : 0;
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
						  }	
			};
			$scope.segmentTable.filter();		
			
			
		});

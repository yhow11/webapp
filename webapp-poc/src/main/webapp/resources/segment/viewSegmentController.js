'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ViewSegmentController
 * @description # ViewSegmentController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ViewSegmentController',
		function($rootScope, $scope, $q,  $timeout, segmentService) {
			$scope.segmentTable = {
					searchValue: null,
					data: {},
					filter: function(){
						segmentService.getPage($scope.segmentTable.searchValue, $scope.segmentTable.query.page, $scope.segmentTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.segmentTable.data.items = data.data.data;
								$scope.segmentTable.data.count = data.data.count;
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
					      return $scope.segmentTable.data.count ? $scope.segmentTable.data.count : 0;
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

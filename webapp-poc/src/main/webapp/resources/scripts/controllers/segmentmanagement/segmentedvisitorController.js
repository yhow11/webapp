'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:SegmentedVisitorController
 * @description # SegmentedVisitorController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'SegmentedVisitorController',
		function($rootScope, $scope, $q,  $timeout, $stateParams ,segmentedVisitorService) {
			
			segmentedVisitorService.count($stateParams.id).then(function(data){
				$scope.totalCount = data.data.count;
			});
			
			$scope.segmentedvisitorTable = {
					searchValue: null,
					filter: function(){
						segmentedVisitorService.getAll($stateParams.id, $scope.segmentedvisitorTable.query.page, $scope.segmentedvisitorTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.segmentedvisitorTable.items = data.data.data;
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
				    	$scope.segmentedvisitorTable.filter();
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
			$scope.segmentedvisitorTable.filter();		
			
		});

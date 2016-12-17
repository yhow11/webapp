'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:SegmentedVisitorController
 * @description # SegmentedVisitorController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'SegmentedVisitorController',
		function($rootScope, $scope, $q,  $timeout, $stateParams ,segmentedVisitorService) {
			
			$scope.segmentedvisitorTable = {
					searchValue: null,
					data: {},
					filter: function(){
						segmentedVisitorService.getPage($stateParams.id, $scope.segmentedvisitorTable.query.page, $scope.segmentedvisitorTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.segmentedvisitorTable.data.items = data.data.data;
								$scope.segmentedvisitorTable.data.count = data.data.count;
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
					      return $scope.segmentedvisitorTable.data.count ? $scope.segmentedvisitorTable.data.count : 0;
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

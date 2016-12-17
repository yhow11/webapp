'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:SummaryMetricController
 * @description # SummaryMetricController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'SummaryMetricController',
		function($rootScope, $scope, $state, metricSummaryService) {
			
			$scope.metricSummaryTable = {
					searchValue: null,
					data: {},
					filter: function(){
						metricSummaryService.getPage($scope.metricSummaryTable.metricType, $scope.metricSummaryTable.query.page, $scope.metricSummaryTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.metricSummaryTable.data.items = data.data.data;
								$scope.metricSummaryTable.data.count = data.data.count;
							}
						});
					},
					metricTypeOptions: [{ name: 'All', type:'ALL'}, { name: 'Page Count', type: 'PAGECOUNT'}, { name: 'Time On Page', type: 'TIMEONPAGE'}, { name: 'Key Sum', type: 'KEYSUM'}],
					items: [],
					query: {
							    order: 'name',
							    limit: 5,
							    page: 1
				    },
				    onPaginate: function(page, limit) {
				    	$scope.metricSummaryTable.filter();
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
					      return $scope.metricSummaryTable.data.count ? $scope.metricSummaryTable.data.count : 0;
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
			
			$scope.metricSummaryTable.metricType = $scope.metricSummaryTable.metricTypeOptions[0].type;
			$scope.metricSummaryTable.filter();		
			
			$scope.$watch("metricSummaryTable.metricType", function() {
				$scope.metricSummaryTable.filter();	
		    });
			
		});
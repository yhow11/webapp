'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ActiveController
 * @description # ActiveController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ActiveController',
		function($rootScope, $scope, $q,  $timeout, $mdDialog, $mdMedia, activeVisitorService) {
			activeVisitorService.count("").then(function(data){
				$scope.totalCount = data.data.count;
			});
			
			$scope.activeVisitorTable = {
					searchValue: null,
					filter: function(){
						activeVisitorService.getAll($scope.activeVisitorTable.filterType, $scope.activeVisitorTable.query.page, $scope.activeVisitorTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.activeVisitorTable.items = data.data.data;
							}
						});
					},
					filterTypes: [{ name: 'All', type:'ALL'}, { name: 'Last Hour', type: 'HOUR'}, { name: 'Last Day', type: 'DAY'}, { name: 'Last Week', type: 'WEEK'}, { name: 'Last Month', type: 'MONTH'}],
					items: [],
					query: {
							    order: 'name',
							    limit: 5,
							    page: 1
				    },
				    onPaginate: function(page, limit) {
				    	$scope.activeVisitorTable.filter();
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
			$scope.activeVisitorTable.filterType = $scope.activeVisitorTable.filterTypes[0].type;
			$scope.activeVisitorTable.filter();		
			
			$scope.$watch("activeVisitorTable.filterType", function() {
				$scope.activeVisitorTable.filter();	
		    });
			
		});

'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ActiveVisitorController
 * @description # ActiveVisitorController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ActiveVisitorController',
		function($rootScope, $scope, $q,  $timeout, $mdDialog, $mdMedia, activeVisitorService) {
			$scope.activeVisitorTable = {
					searchValue: null,
					data: {},
					filter: function(){
						activeVisitorService.getPage($scope.activeVisitorTable.filterType, $scope.activeVisitorTable.query.page, $scope.activeVisitorTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.activeVisitorTable.data.items = data.data.data;
								$scope.activeVisitorTable.data.count = data.data.count;
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
					      return $scope.activeVisitorTable.data.count ? $scope.activeVisitorTable.data.count : 0;
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

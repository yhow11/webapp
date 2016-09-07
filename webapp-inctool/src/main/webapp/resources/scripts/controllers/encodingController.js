'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:EncodingController
 * @description # EncodingController Controller of the incToolApp
 */
angular.module('incToolApp').controller(
		'EncodingController',
		function($rootScope, $scope, $stateParams, $mdDialog, $mdMedia, $state, memberService, $log) {
			$scope.search = false;
			$scope.status = '  ';
		
			$scope.options = function(option, member){
				if(option.name == "Edit") {
					$state.go("membership", { id: member.id});	
				} else if(action.name == "Delete") {
					memberService.remove(member.id).then(function(data){
						if(data.status == 200) {
							 if(data.data.status) {
								 $state.go($state.current, {}, {reload: true});
							 }
						 }
						 
					});
				}
			};

			// MD table
			$scope.memberTable = {
					searchValue: null,
					search: function(searchForm){
						memberService.getAll({reference: $scope.memberTable.searchValue}, $scope.memberTable.query.page, $scope.memberTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.memberTable.items = data.data;
								
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
					   	 memberService.getAll({reference: $scope.memberTable.searchValue}, page, limit).then(function(data){
							 if(data.status == 200) {
								 $scope.memberTable.items = data.data.data;
							 }
							 
						});
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
					      return $scope.memberTable.items ? $scope.memberTable.items.count : 0;
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
			}
			$scope.tableAction = function(ev, action, member) {
				if (action.name == 'Edit') {
					$scope.showAdvanced(ev, member);
				}
			};

			
			$scope.selected = [];
			memberService.getAll(null, $scope.memberTable.query.page, $scope.memberTable.query.limit).then(function(data){
				 if(data.status == 200) {
					 $scope.memberTable.items = data.data.data;
				 }
				 
			});

		});

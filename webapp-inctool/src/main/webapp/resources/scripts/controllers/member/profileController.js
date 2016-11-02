'use strict';
/**
 * @ngdoc function
 * @name incToolApp.controller:MemberProfileController
 * @description # MemberProfileController Controller of the incToolApp
 */
angular.module('incToolApp').controller(
		'MemberProfileController',
		function($rootScope, $scope, $stateParams, $q, $timeout, workerService,  memberService) {
			
			$scope.memberProfile = {
					field: {
						reference : {
							value: null,
							searchText: null,
							search: function(query) {
								var deferred = $q.defer();
								workerService.getAll(query,"","").then(function(data){
									if(data.data){
										deferred.resolve( data.data.data );
									}
								});
								return deferred.promise;
				            }
						}
					},
					data: null,
					init: function(){
						memberService.get($stateParams.id).then(function(data){
							 if(data.status == 200) {
								 if(data.data.status) {
									 $scope.memberProfile.data = data.data.data[0];
//									 $scope.datas = [Number(data.data.data[0].present), Number(data.data.data[0].absent), 48-(Number(data.data.data[0].present)+Number(data.data.data[0].absent))];
								 }
								 
							 }
							 
						});
					},
					save: function(){
						memberService.save($scope.memberProfile.data).then(function(data){
							 if(data.status == 200) {
								 if(data.data.status) {
									 $rootScope.goTo("encoding");
								 }
								 
							 }
							 
						});
					}
			}
			$scope.memberProfile.init();
			
			var reference = {
					simulateQuery: true,
					items: ["Create New"],
					isDisabled: false,
					noCache: false,
					selectedItem: null,
					searchText: null,
				    selectedItemChanged: function selectedItemChange(item) {
				        $log.info('Item changed to ' + JSON.stringify(item));
				    }
					
			};
			reference.createFilterFor = function createFilterFor(query) {
		        var lowercaseQuery = angular.lowercase(query);
		        return function filterFn(item) {
		          return (item.indexOf(lowercaseQuery) === 0);
		        };
	        };
	        
	        reference.querySearch = function(query) {
	            var results = query ? $scope.reference.items.filter( $scope.reference.createFilterFor(query) ) : $scope.reference.items,
	                    deferred;
	                if ($scope.reference.simulateQuery) {
	                  deferred = $q.defer();
	                  $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
	                  return deferred.promise;
	                } else {
	                  return results;
	                }
            };
            $scope.reference = reference;
            $scope.reference.createNew = function(item){
	        	$scope.reference.items.push(item);
	        };
			$scope.isSpeedDialOpen = false;
			$scope.labels = ["Attended", "Absent/s", "Left"];
			$scope.datas = [];
			$scope.showLabel = true;  
			
			
			$scope.change = function(date, index) {
				$scope.member.r310[index] = date;
				$scope.member;
			}
		});

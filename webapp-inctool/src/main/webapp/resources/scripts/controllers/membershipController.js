'use strict';
/**
 * @ngdoc function
 * @name incToolApp.controller:MembershipController
 * @description # MembershipController Controller of the incToolApp
 */
angular.module('incToolApp').controller(
		'MembershipController',
		function($rootScope, $scope, $stateParams, $q, $timeout,  memberService) {
			var reference = {
					simulateQuery: true,
					items: ["123","111","222"],
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
			memberService.get($stateParams.id).then(function(data){
				 if(data.status == 200) {
					 if(data.data.status) {
						 $scope.member = data.data.data[0];
						 $scope.datas = [Number(data.data.data[0].present), Number(data.data.data[0].absent), 48-(Number(data.data.data[0].present)+Number(data.data.data[0].absent))];
					 }
					 
				 }
				 
			});
			
			$scope.save = function() {
				memberService.save($scope.member).then(function(data){
					 if(data.status == 200) {
						 if(data.data.status) {
							 $rootScope.goTo("encoding");
						 }
						 
					 }
					 
				});
			}
			$scope.change = function(date, index) {
				$scope.member.r310[index] = date;
				$scope.member;
			}
		});

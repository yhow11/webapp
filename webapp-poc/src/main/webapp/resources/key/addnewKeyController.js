'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:AddNewKeyController
 * @description # AddNewKeyController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'AddNewKeyController',
		function($rootScope, $scope, $stateParams, keyService) {
			
			$scope.initData = function(){
				$scope.data = {
						key: "",
						values: []
				};
				$scope.isOnEdit = false;
				if($stateParams.param != null){
					$scope.data = $stateParams.param;
					$scope.isOnEdit = true;
				}
			}
			$scope.initData();
			$scope.keymanager = {};
			$scope.keymanager.add = function(form, key, value) {
				if(form.newValue.$valid){
					$scope.data.values.push({key: key, value: value});
					$scope.newValue = '';
				}
			};
			
			$scope.keymanager.remove = function(index) {
				$scope.data.values.splice(index, 1);
			};
			$scope.save = function(form) {
				keyService.save($scope.data).then(function(data){
					if(data.data.status){
						$rootScope.toast("Success!", "md-primary");
						$scope.initData();
						form.$setPristine();
						form.$setUntouched();
						form.$error = {};
					}
				});
			}
		});
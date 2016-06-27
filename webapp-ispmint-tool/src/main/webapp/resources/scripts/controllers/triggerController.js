'use strict';
/**
 * @ngdoc function
 * @name ispMintApp.controller:TriggerController
 * @description # TriggerController Controller of the ispMintApp
 */
angular.module('ispMintApp').controller(
		'TriggerController',
		function($scope, $stateParams, $mdDialog, $mdMedia, $state, triggerService, $log) {

			$scope.status = '  ';
			$scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
			$scope.showAdvanced = function(ev, member) {
				var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))
						&& $scope.customFullscreen;

				$mdDialog.show({
					controller : RegistraionController,
					templateUrl : 'resources/views/dialog/registration.html',
					parent : angular.element(document.body),
					targetEvent : ev,
					locals : {
						member : member
					},
					clickOutsideToClose : true,
					fullscreen : useFullScreen
				}).then(
						function(answer) {
							$scope.status = 'You said the information was "'
									+ answer + '".';
						}, function() {
							$scope.status = 'You cancelled the dialog.';
						});
				$scope.$watch(function() {
					return $mdMedia('xs') || $mdMedia('sm');
				}, function(wantsFullScreen) {
					$scope.customFullscreen = (wantsFullScreen === true);
				});

				function RegistraionController($scope, $mdDialog, member) {
					$scope.closeDialog = function() {
						$mdDialog.hide();
					};
					$scope.showHints = false;
					$scope.member = member;
				}
				;
			};

			// MD table
			$scope.tableAction = function(ev, action, member) {
				if (action.name == 'Edit') {
					$scope.showAdvanced(ev, member);
				}
			};

			
			$scope.selected = [];

			 triggerService.getAll().then(function(data){
				 if(data.status == 200) {
					 if(data.data.status) {
						 $scope.triggers = data.data.data;
					 }
					 
				 }
				 
			});

		});

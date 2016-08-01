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
			$scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
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

			 memberService.getAll().then(function(data){
				 if(data.status == 200) {
					 if(data.data.status) {
						 $scope.members = data.data.data;
					 }
					 
				 }
				 
			});

		});

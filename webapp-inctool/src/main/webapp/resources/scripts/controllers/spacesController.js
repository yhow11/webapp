'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:SpacesCtrl
 * @description # SpacesCtrl Controller of the sbAdminApp
 */
angular.module('sbAdminApp').controller('SpacesCtrl',
		function($scope, $stateParams) {
		$scope.space = $stateParams.space;
		});
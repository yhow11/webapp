'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:ApplicationCtrl
 * @description # ApplicationCtrl Controller of the sbAdminApp
 */
angular.module('sbAdminApp').controller('ApplicationCtrl',
		function($scope, $stateParams) {
			$scope.application = $stateParams.application;

		});
'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description # adminPosHeader
 */

angular.module('sbAdminApp').directive('sidebar', [ '$location', function() {
	return {
		templateUrl : 'resources/scripts/directives/sidebar/sidebar.html',
		restrict : 'E',
		controller : function($scope, $rootScope, $state) {
			$scope.orgs = $rootScope.orgs;
			$scope.selectedMenu = 'dashboard';
			$scope.collapseVar = 100;
			$scope.multiCollapseVar = 100;
			
			$scope.check = function(x, org) {
				$state.go("dashboard.home", { org: org });
				if (x == $scope.collapseVar)
					$scope.collapseVar = 0;
				else
					$scope.collapseVar = x;
			};
			$scope.displaySpace = function(y, space) {
				$state.go("dashboard.spaces", { space: space});
				if(y==$scope.multiCollapseVar)
		            $scope.multiCollapseVar = 0;
		          else
		            $scope.multiCollapseVar = y;
			};
			
			$scope.displayApp = function(app) {
				$state.go("dashboard.apps", { application: app});
			};
		}
	}
} ]);

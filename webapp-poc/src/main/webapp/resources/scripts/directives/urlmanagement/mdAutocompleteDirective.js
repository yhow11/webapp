'use strict';

angular.module('fingerPrintApp').directive("valueAutocomplete",
		function($q, $timeout, $rootScope, keymanagementService) {
			return {
				link : function(scope, element, attributes, ngModel) {
					console.log(element[0].querySelector('input').html());
				}
			};
		});

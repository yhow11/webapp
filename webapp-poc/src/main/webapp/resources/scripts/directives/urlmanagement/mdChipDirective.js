'use strict';

angular.module('fingerPrintApp').directive("mdChip",
		function($q, $timeout, $rootScope, keymanagementService) {
			return {
				scope : {
					'chip' : '=',
					'popover' : '='
				},
				link : function(scope, element, attributes, ngModel) {
					if(scope.chip.popupDisplay){
						document.querySelector('.keyAutoComplete').blur();
						var x = element.prop('offsetLeft');
						var y = element.prop('offsetTop');
						console.log(x + ":" + y);
						$timeout(function() {
							$rootScope.$broadcast('show-popover', { data: { x: x, y: y, chip: scope.chip } });
						});
					}
				
				}
			};
		});

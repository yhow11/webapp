'use strict';

angular.module('fingerPrintApp')
    .directive("validvalue", function($q, keyService) {
    return {
        restrict: "A",
         
        require: "ngModel",
        scope: {
            items: '=validvalue'
          },
        link: function(scope, element, attributes, ngModel) {
            ngModel.$validators.validvalue = function(modelValue) {  
            	var isValid = true;
				for(var item in scope.items){
					if(scope.items[item].value == modelValue) {
						isValid = false;
					}
				}
            	return isValid;
            }
        }
    };
});

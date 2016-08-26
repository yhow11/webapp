'use strict';

angular.module('fingerPrintApp')
    .directive("validkey", function($q, keymanagementService) {
    return {
        restrict: "A",
         
        require: "ngModel",
        scope: {
            isOnEdit: '=validkey'
          }, 
        link: function(scope, element, attributes, ngModel) {
        	if(!scope.isOnEdit){
        		ngModel.$asyncValidators.validkey = function(modelValue) {  
               	 var defer = $q.defer();
               	keymanagementService.getByKey(modelValue).then(function(data){
               		if(data.data.status){
               			defer.resolve();
               		} else {
               			defer.reject();
               		}
               		
               	});
               	
               	return defer.promise;
               }
        	}
            
        }
    };
});

'use strict';

angular.module('fingerPrintApp')
    .factory('segmentConditionService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(metrictype) {
           return $http.get('segment/filter/getAll?metrictype='+metrictype).then(handleSuccess, handleError('Error getting all users'));
       }
   }
});

'use strict';

angular.module('fingerPrintApp')
    .factory('metricSummaryService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(type, offset, limit) {
		   return $http.get('metric/summary/getAll?type='+type+'&offset='+offset+'&limit='+limit).then(handleSuccess, handleError('Error getting all users'));
	  }
   }
});

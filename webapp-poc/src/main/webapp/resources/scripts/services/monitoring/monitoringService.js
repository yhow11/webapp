'use strict';

angular.module('fingerPrintApp')
    .factory('monitoringService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(offset, limit) {
           return $http.get('monitoring/getAll?offset='+offset+'&limit='+limit).then(handleSuccess, handleError('Error getting all users'));
       }
   }
});

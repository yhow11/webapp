'use strict';

angular.module('fingerPrintApp')
    .factory('webEventService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(data, start, end) {
           return $http.get('webevent/getAll?start='+start+'&end='+end, data).then(handleSuccess, handleError('Error getting all users'));
       }
       
   }
});

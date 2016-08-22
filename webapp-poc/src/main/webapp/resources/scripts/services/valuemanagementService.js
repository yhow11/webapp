'use strict';

angular.module('fingerPrintApp')
    .factory('valuemanagementService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(key) {
           return $http.get('valuemanagement/getAll?key='+key).then(handleSuccess, handleError('Error getting all users'));
       }
       
   }
});

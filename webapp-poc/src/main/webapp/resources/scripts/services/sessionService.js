'use strict';

angular.module('fingerPrintApp')
    .factory('sessionService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(sessionID, browserFP) {
           return $http.get('session/getAll?sessionID='+sessionID+'&browserFP='+browserFP).then(handleSuccess, handleError('Error getting all users'));
       }
   }
});

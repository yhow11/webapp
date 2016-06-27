'use strict';

angular.module('ispMintApp')
    .factory('spaceService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function() {
           return $http.post('/podio/api/organization/get/organization').then(handleSuccess, handleError('Error getting user by username'));
       }
       
   }
});

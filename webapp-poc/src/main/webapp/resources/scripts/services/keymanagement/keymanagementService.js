'use strict';

angular.module('fingerPrintApp')
    .factory('keymanagementService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(value, start, end) {
           return $http.get('keymanagement/getAll?value='+value+'&start='+start+'&end='+end).then(handleSuccess, handleError('Error getting all users'));
       },
       getById: function(id) {
           return $http.get('keymanagement/getById/' + id).then(handleSuccess, handleError('Error getting user by id'));
       },
       checkExists: function(key) {
           return $http.get('keymanagement/search?key='+key).then(handleSuccess, handleError('Error getting user by id'));
       },

       save: function(key) {
           return $http.post('keymanagement/save', key).then(handleSuccess, handleError('Error creating user'));
       },

       remove: function(key) {
           return $http.delete('keymanagement/delete?key=' + key).then(handleSuccess, handleError('Error deleting user'));
       }
       
   }
});

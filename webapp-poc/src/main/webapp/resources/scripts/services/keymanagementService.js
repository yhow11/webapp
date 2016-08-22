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
	   getAll: function(key) {
           return $http.get('keymanagement/getAll?key='+key).then(handleSuccess, handleError('Error getting all users'));
       },
	   getAllByPagination: function(start, end) {
           return $http.get('keymanagement/getAllByPagination?start='+start+'&end='+end).then(handleSuccess, handleError('Error getting all users'));
       },
       get: function(data, start) {
           return $http.post('keymanagement/get?start='+start, data).then(handleSuccess, handleError('Error getting all users'));
       },
       getById: function(id) {
           return $http.get('keymanagement/getById/' + id).then(handleSuccess, handleError('Error getting user by id'));
       },
       getByKey: function(key) {
           return $http.get('keymanagement/getByKey?key='+key).then(handleSuccess, handleError('Error getting user by id'));
       },

       save: function(key) {
           return $http.post('keymanagement/save', key).then(handleSuccess, handleError('Error creating user'));
       },

       remove: function(id) {
           return $http.delete('keymanagement/delete?id=' + id).then(handleSuccess, handleError('Error deleting user'));
       }
       
   }
});

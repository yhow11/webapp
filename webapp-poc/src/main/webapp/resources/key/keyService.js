'use strict';

angular.module('fingerPrintApp')
    .factory('keyService', function($http) {
  
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
           return $http.get('key/getAll?value='+value+'&start='+start+'&end='+end).then(handleSuccess, handleError('Error getting all users'));
       },
       getPage: function(value, page, limit) {
    	   if(value != null) value = "%"+value+"%";
           return $http.post('key/getPage/', {page:page,limit:limit,model:{key:value}}).then(handleSuccess, handleError('Error getting user by id'));
       },
       getById: function(id) {
           return $http.get('key/getById/' + id).then(handleSuccess, handleError('Error getting user by id'));
       },
       checkExists: function(key) {
           return $http.get('key/checkExists?key='+key).then(handleSuccess, handleError('Error getting user by id'));
       },

       save: function(key) {
           return $http.post('key/save', key).then(handleSuccess, handleError('Error creating user'));
       },

       remove: function(key) {
           return $http.delete('key/delete?key=' + key).then(handleSuccess, handleError('Error deleting user'));
       }
       
   }
});

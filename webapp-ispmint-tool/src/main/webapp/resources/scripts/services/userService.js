'use strict';

angular.module('sbAdminApp')
    .factory('userService', function($http) {
  
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
           return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
       },

       getById: function(id) {
           return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
       },

       get: function(username, password) {
           return $http.post('/podio/authorization/login', { username: username, password: password }).then(handleSuccess, handleError('Error getting user by username'));
       },

       create: function(user) {
           return $http.post('/api/users', user).then(handleSuccess, handleError('Error creating user'));
       },

       update: function(user) {
           return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
       },

       remove: function(id) {
           return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
       }
       
   }
});

'use strict';

angular.module('fingerPrintApp')
    .factory('eventService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(start) {
           return $http.get('event/getAll?start='+start).then(handleSuccess, handleError('Error getting all users'));
       },
       getAllWebEvents: function(data, start) {
           return $http.post('event/getWebEvents?start='+start, data).then(handleSuccess, handleError('Error getting all users'));
       },
       getAnonymousUser: function(data) {
           return $http.post('event/getAnonymousUser', data).then(handleSuccess, handleError('Error getting all users'));
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

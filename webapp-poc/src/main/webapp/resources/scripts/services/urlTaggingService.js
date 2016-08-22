'use strict';

angular.module('fingerPrintApp')
    .factory('urlTaggingService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAllURLs: function(url, page, limit) {
           return $http.get('urltagging/getAllURLs?url='+url+'&page='+page+"&limit="+limit).then(handleSuccess, handleError('Error getting all users'));
       },
       getAllWebEvents: function(data, start) {
           return $http.post('urltagging/getWebEvents?start='+start, data).then(handleSuccess, handleError('Error getting all users'));
       },
       getMySessions: function(data) {
           return $http.post('urltagging/getSessions', data).then(handleSuccess, handleError('Error getting all users'));
       },
       getAnonymousUser: function(data) {
           return $http.post('urltagging/getAnonymousUser', data).then(handleSuccess, handleError('Error getting all users'));
       },
     
       save: function(items) {
           return $http.post('urltagging/save', items).then(handleSuccess, handleError('Error creating user'));
       },

       update: function(user) {
           return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
       },

       remove: function(id) {
           return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
       }
       
   }
});

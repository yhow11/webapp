'use strict';

angular.module('ispMintApp')
    .factory('triggerService', function($http) {
  
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
           return $http.post('api/member/getAll').then(handleSuccess, handleError('Error getting all members'));
       },

       get: function(id) {
           return $http.post('api/member/get?id=' + id).then(handleSuccess, handleError('Error getting member by id'));
       },
       getTemplate: function(){
    	   return $http.post('api/member/getTemplate').then(handleSuccess, handleError('Error getting member by id')); 
       },
       save: function(member) {
           return $http.post('api/member/save', member).then(handleSuccess, handleError('Error creating member'));
       },
       remove: function(id) {
           return $http.delete('api/member/remove' + id).then(handleSuccess, handleError('Error deleting member'));
       }
       
   }
});

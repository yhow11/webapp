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
           return $http.get('urltagging/getAll?url='+url+'&page='+page+"&limit="+limit).then(handleSuccess, handleError('Error getting all users'));
       },
       save: function(items) {
           return $http.post('urltagging/save', items).then(handleSuccess, handleError('Error creating user'));
       }
       
   }
});

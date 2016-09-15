'use strict';

angular.module('fingerPrintApp')
    .factory('URLImportService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(sitemapURL) {
           return $http.get('urlimport/getAll?sitemapURL='+sitemapURL).then(handleSuccess, handleError('Error getting all users'));
       },
       save: function(urls) {
           return $http.post('urlimport/save', urls).then(handleSuccess, handleError('Error creating user'));
       }
   }
});

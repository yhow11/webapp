'use strict';

angular.module('fingerPrintApp')
    .factory('metricTypeService', function($http) {
  
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
		   return $http.get('metrictype/getAll').then(handleSuccess, handleError('Error getting all users'));
	  },
	  save: function(item) {
		   return $http.get('metrictype/save', item).then(handleSuccess, handleError('Error getting all users'));
	  }
   }
});

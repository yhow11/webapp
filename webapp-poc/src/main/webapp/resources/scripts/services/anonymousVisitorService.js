'use strict';

angular.module('fingerPrintApp')
    .factory('anonymousVisitorService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getDetail: function(data) {
		   return $http.post('anonymousvisitor/getDetail', data).then(handleSuccess, handleError('Error getting all users'));
	  }
   }
});

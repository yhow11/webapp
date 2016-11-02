'use strict';

angular.module('fingerPrintApp')
    .factory('activeVisitorService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(value, page, limit) {
           return $http.get('activevisitor/getPage?value='+value+'&page='+page+'&limit='+limit).then(handleSuccess, handleError('Error getting all users'));
       },
       count: function(value) {
           return $http.get('activevisitor/count?value='+value).then(handleSuccess, handleError('Error getting all users'));
       }
   }
});

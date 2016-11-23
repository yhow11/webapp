'use strict';

angular.module('fingerPrintApp')
    .factory('segmentedVisitorService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(segmentID, page, limit) {
           return $http.get('segmentedvisitor/getBook?segmentID='+segmentID+'&page='+page+'&limit='+limit).then(handleSuccess, handleError('Error getting all users'));
       },
       get: function(id) {
           return $http.get('segmentedvisitor/get?id='+id).then(handleSuccess, handleError('Error getting all users'));
       },
       count: function(segmentID) {
           return $http.get('segmentedvisitor/count?segmentID='+segmentID).then(handleSuccess, handleError('Error getting all users'));
       }
   }
});

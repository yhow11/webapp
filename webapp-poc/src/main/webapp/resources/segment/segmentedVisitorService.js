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
 	   getPage: function(value, page, limit) {
 	          return $http.post('segmentedvisitor/getPage/', {page:page,limit:limit,model:{segmentID:value}}).then(handleSuccess, handleError('Error getting user by id'));
       },
       get: function(id) {
           return $http.get('segmentedvisitor/get?id='+id).then(handleSuccess, handleError('Error getting all users'));
       },
       count: function(segmentID) {
           return $http.get('segmentedvisitor/count?segmentID='+segmentID).then(handleSuccess, handleError('Error getting all users'));
       }
   }
});

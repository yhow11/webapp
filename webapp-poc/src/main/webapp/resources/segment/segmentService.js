'use strict';

angular.module('fingerPrintApp')
    .factory('segmentService', function($http) {
  
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
           return $http.get('segment/getBook?value='+value+'&page='+page+'&limit='+limit).then(handleSuccess, handleError('Error getting all users'));
       },
 	   getPage: function(value, page, limit) {
 	   	   if(value != null) value = "%"+value+"%";
 	          return $http.post('segment/getPage/', {page:page,limit:limit,model:{name:value}}).then(handleSuccess, handleError('Error getting user by id'));
       },
       get: function(id) {
           return $http.get('segment/get?id='+id).then(handleSuccess, handleError('Error getting all users'));
       },
       save: function(model) {
           return $http.post('segment/save', JSON.stringify(model)).then(handleSuccess, handleError('Error getting all users'));
       },
       count: function(query) {
           return $http.get('segment/count?query='+query).then(handleSuccess, handleError('Error getting all users'));
       }
   }
});

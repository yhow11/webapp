'use strict';

angular.module('fingerPrintApp')
    .factory('metricService', function($http) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(offset, limit) {
		   return $http.get('metric/getAll?offset='+offset+'&limit='+limit).then(handleSuccess, handleError('Error getting all users'));
	  },
	  getPage: function(value, page, limit) {
   	   if(value != null) value = "%"+value+"%";
          return $http.post('metric/getPage/', {page:page,limit:limit,model:{name:value}}).then(handleSuccess, handleError('Error getting user by id'));
      },
	  search: function(query){
		  return  $http.get('metric/search?query='+query).then(handleSuccess, handleError('Error getting all users'));
	  },
	  get: function(id) {
		   return $http.get('metric/get?id='+id).then(handleSuccess, handleError('Error getting all users'));
	  },
	  save: function(item) {
		   return $http.post('metric/save', item).then(handleSuccess, handleError('Error getting all users'));
	  },
	  remove: function(item) {
		   return $http.post('metric/remove', item).then(handleSuccess, handleError('Error getting all users'));
	  }
   }
});

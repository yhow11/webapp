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
       getPage : function(value, page, limit) {
				
			return $http.post('activevisitor/getPage?value='+value, {
				page : page,
				limit : limit,
				model : {}
			}).then(handleSuccess,
					handleError('Error getting user by id'));
		}
   }
});

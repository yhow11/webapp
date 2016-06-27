'use strict';

angular.module('sbAdminApp')
    .factory('broadcaseService', function($rootScope) {
  
    	function handleSuccess(data) {
            return data;
        }

    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   org: function() {
		  return{
			  display:  function() {
				   $rootScope.$broadcast("displayOrg");
		       },
		       listenDisplay: function(callback) {
		    	   $rootScope.$on("displayOrg",callback);
		       }
		  }
	   }
       
   }
});

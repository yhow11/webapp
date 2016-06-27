'use strict';

angular.module('ispMintApp')
    .factory('organizationService', function($http, $localStorage, $rootScope ) {
  
   return {
       getAll: function() {
    	  return $http.get('/webapp-ispmint-tool/organization/getAll');
	    },
	    get: function(id) {
	      return $http.post('/webapp-ispmint-tool/organization/get', { id: id});
	    }
   }
});

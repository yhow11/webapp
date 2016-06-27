'use strict';

angular.module('ispMintApp')
    .factory('appService', function($http, $localStorage, $rootScope ) {
  
   return {
       getAll: function(id) {
    	  return $http.get('/webapp-ispmint-tool/app/getAll?workspaceID='+id);
	    },
	    get: function(id) {
	      return $http.post('/webapp-ispmint-tool/app/get', { id: id});
	    }
   }
});

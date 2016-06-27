'use strict';

angular.module('ispMintApp')
    .factory('itemService', function($http, $localStorage, $rootScope ) {
  
   return {
       getAll: function() {
    	  return $http.get('/webapp-ispmint-tool/item/getAll?appID=15183204');
	    },
	    get: function(id) {
	      return $http.post('/webapp-ispmint-tool/workflow/get', { id: id});
	    }
   }
});

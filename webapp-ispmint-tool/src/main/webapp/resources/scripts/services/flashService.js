'use strict';

angular.module('sbAdminApp')
    .factory('flashService', function($rootScope) {
  
    	initService();

    	function initService() {
            $rootScope.$on('$locationChangeStart', function () {
                clearFlashMessage();
            });

            function clearFlashMessage() {
                var flash = $rootScope.flash;
                if (flash) {
                    if (!flash.keepAfterLocationChange) {
                        delete $rootScope.flash;
                    } else {
                        // only keep for a single location change
                        flash.keepAfterLocationChange = false;
                    }
                }
            }
        }
    	
   return {
	   success: function(message, keepAfterLocationChange) {
           $rootScope.flash = {
               message: message,
               type: 'success', 
               keepAfterLocationChange: keepAfterLocationChange
           };
       },
       error: function(message, keepAfterLocationChange) {
           $rootScope.flash = {
               message: message,
               type: 'error',
               keepAfterLocationChange: keepAfterLocationChange
           };
       }
   
   }
});

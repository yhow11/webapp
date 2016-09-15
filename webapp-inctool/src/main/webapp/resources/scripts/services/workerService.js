'use strict';

angular.module('incToolApp')
    .factory('workerService', function($http) {
  
    	function handleSuccess(data) {
    		for(var index in data.data.data) {
    			var member = data.data.data[index];
        		for(var indexDate in member.r310){
        			var date = member.r310[indexDate].date;
        			if(date != null){
        				var newDate = new Date(date);
            			member.r310[indexDate].datepicker = newDate;
        			}
        		}
        		for(var indexDate in member.r305){
        			var date = member.r305[indexDate].date;
        			if(date != null){
        				var newDate = new Date(date);
            			member.r305[indexDate].datepicker = newDate;
        			}
        		}
        		for(var indexDate in member.r309){
        			var date = member.r309[indexDate].date;
        			if(date != null){
        				var newDate = new Date(date);
            			member.r309[indexDate].datepicker = newDate;
        			}
        		}
        		 data.data.data[index] = member;
    		}
    		
            return data;
        }
    	function handleRemoveSuccess(data) {
    		
    		
            return data;
        }
    	function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
   return {
	   getAll: function(data, page, limit) {
           return $http.post('api/worker/getAll', {page: page, limit: limit, data: data}).then(handleSuccess, handleError('Error getting all members'));
       },

       get: function(id) {
           return $http.post('api/worker/get?id=' + id).then(handleSuccess, handleError('Error getting member by id'));
       },
       getTemplate: function(){
    	   return $http.post('api/worker/getTemplate').then(handleSuccess, handleError('Error getting member by id')); 
       },
       save: function(worker) {
           return $http.post('api/worker/save', JSON.stringify(worker)).then(handleSuccess, handleError('Error creating member'));
       },
       remove: function(id) {
           return $http.post('api/worker/remove?id=' + id).then(handleRemoveSuccess, handleError('Error deleting member'));
       }
       
   }
});

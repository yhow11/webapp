'use strict';

angular.module('fingerPrintApp').
    factory('LogsInfiniteScroll', function($http, logsService) {
  var LogsInfiniteScroll = function() {
    this.items = [];
    this.busy = false;
    this.after = '0';
    this.limit = '50';
  };

  LogsInfiniteScroll.prototype.nextPage = function() {
	  var self = this;
    if (this.busy) return;
    this.busy = true;
    
    logsService.getAll(this.after,  String(Number(this.after) + Number(this.limit))).then(function(data){
    	if(data.data){
    		if(data.data.status) {
    			var items = data.data.data;
    			if(items.length > 1) {
    				 this.after = items[items.length - 1].timeStamp;
    				 for (var i = 0; i < items.length; i++) {
    					 items[i].timeStamp = moment(new Date(Number(items[i].timeStamp))).format("LLL")
    			    	  this.items.push(items[i]);
    			      }
    		     
    			}
    			
    			this.busy = false;
    		}
    	}
		
	}.bind(this));
    return LogsInfiniteScroll;
  };
  return LogsInfiniteScroll;
});

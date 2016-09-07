
'use strict';

angular.module('fingerPrintApp').
    factory('KeymanagementInfiniteScroll', function($http, keymanagementService) {
  var KeymanagementInfiniteScroll = function() {
    this.items = [];
    this.busy = false;
    this.after = '0';
    this.limit = '50';
  };

  KeymanagementInfiniteScroll.prototype.refreshPage = function() {
	  var self = this;
	  if (this.busy) return;
	    this.busy = true;
    
	    keymanagementService.getAllByPagination('0', this.limit, '').then(function(data){
    	if(data.data){
    		if(data.data.status) {
    			self.items = data.data.data;
    			self.after = self.items[self.items.length - 1].id;
    			self.busy = false;
    		}
    	}
		
	}.bind(this));
    return KeymanagementInfiniteScroll;
  };
  
  KeymanagementInfiniteScroll.prototype.nextPage = function(value) {
	  var self = this;
	  if (this.busy) return;
	    this.busy = true;
    
	    keymanagementService.getAllByPagination(this.after,  String(Number(this.after) + Number(this.limit)), value).then(function(data){
    	if(data.data){
    		if(data.data.status) {
    			var items = data.data.data;
    			for (var i = 0; i < items.length; i++) {
    				self.items.push(items[i]);
			     }
    			self.after = String(self.items.length+1);
    			self.busy = false;
    		}
    	}
		
	}.bind(this));
    return KeymanagementInfiniteScroll;
  };
  return KeymanagementInfiniteScroll;
});
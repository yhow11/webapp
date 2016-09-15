
'use strict';

angular.module('fingerPrintApp').
    factory('MetricInfiniteScroll', function($http, metricService) {
  var MetricInfiniteScroll = function() {
    this.items = [];
    this.busy = false;
    this.after = '0';
    this.limit = '50';
  };

  MetricInfiniteScroll.prototype.refreshPage = function() {
	  var self = this;
	  if (this.busy) return;
	    this.busy = true;
    
	    metricService.getAll('0', self.items.length).then(function(data){
    	if(data.data){
    		if(data.data.status) {
    			self.items = data.data.data;
    			self.after = String(self.items.length+1);
    			self.busy = false;
    		}
    	}
		
	}.bind(this));
    return MetricInfiniteScroll;
  };
  
  MetricInfiniteScroll.prototype.nextPage = function(value) {
	  var self = this;
	  if (this.busy) return;
	    this.busy = true;
    
	    metricService.getAll(this.after,  String(Number(this.after) + Number(this.limit))).then(function(data){
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
    return MetricInfiniteScroll;
  };
  return MetricInfiniteScroll;
});
'use strict';

angular.module('fingerPrintApp').
    factory('MonitoringInfiniteScroll', function($http, eventService) {
  var MonitoringInfiniteScroll = function() {
    this.items = [];
    this.busy = false;
    this.after = 0;
  };

  MonitoringInfiniteScroll.prototype.nextPage = function() {
	  var self = this;
    if (this.busy) return;
    this.busy = true;
    
    eventService.getAll(this.after).then(function(data){
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
    return MonitoringInfiniteScroll;
  };
  /*Reddit.prototype.nextWebEventPage = function() {
	  var self = this;
	  console.log(this.busy);
    if (this.busy) return;
    this.busy = true;
    
    eventService.getAllWebEvents(FingerPrint.getData(), this.after).then(function(data){
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
    return Reddit;
  };*/
  return MonitoringInfiniteScroll;
});

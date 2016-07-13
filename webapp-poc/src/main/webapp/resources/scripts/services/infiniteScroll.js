'use strict';

angular.module('fingerPrintApp').
    factory('Reddit', function($http, eventService) {
  var Reddit = function() {
    this.items = [];
    this.busy = false;
    this.after = new Date().getTime();
  };

  Reddit.prototype.nextPage = function() {
	  var self = this;
	  console.log(this.busy);
    if (this.busy) return;
    this.busy = true;
    
    eventService.getAll(this.after).then(function(data){
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
	}.bind(this));
    return Reddit;
  };
  Reddit.prototype.nextWebEventPage = function() {
	  var self = this;
	  console.log(this.busy);
    if (this.busy) return;
    this.busy = true;
    
    eventService.getAllWebEvents(fingerPrintData, this.after).then(function(data){
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
	}.bind(this));
    return Reddit;
  };
  return Reddit;
});

'use strict';

angular.module('fingerPrintApp').
    factory('LogsInfiniteScroll', function($http, logsService) {
  var LogsInfiniteScroll = function() {
    this.items = [];
    this.busy = false;
    this.limit = '50';
  };

  LogsInfiniteScroll.prototype.nextPage = function() {
	  var self = this;
    if (this.busy) return;
    this.busy = true;
    
    logsService.getAll(String(self.items.length+1),  String(self.items.length+1 + Number(this.limit))).then(function(data){
    	if(data.data){
    		if(data.data.status) {
    			var items = data.data.data;
				for (var i = 0; i < items.length; i++) {
					 items[i].timeStamp = moment(new Date(Number(items[i].timeStamp))).format("LLL");
					 self.items.push(items[i]);
			    }
    			self.busy = false;
    		}
    	}
		
	}.bind(this));
    return LogsInfiniteScroll;
  };
  return LogsInfiniteScroll;
});

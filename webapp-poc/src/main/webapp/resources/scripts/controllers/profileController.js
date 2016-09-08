'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:ProfileController
 * @description # ProfileController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'ProfileController',
		function($rootScope, $scope, eventService, sessionService, ProfileInfiniteScroll) {
			
			$scope.profileInfiniteScroll = new ProfileInfiniteScroll();
			$scope.init = function(){
				var params = FingerPrint.getData();
				eventService.getAnonymousUser(params).then(function(data){
					if(data.data.status) {
						$scope.fingerPrintData = data.data.data[0];
					}
				});
				sessionService.getAll(params.sessionID, params.browserFP).then(function(data){
					if(data.data.status) {
						$scope.sessionModels = data.data.data;
					}
				});
				$rootScope.$on("notifyReceivers", function(event, data) {
					var webEvent = data.data;
					if(webEvent.type == "WebEventModel"){
						eventService.getAnonymousUser(FingerPrint.getData()).then(function(data){
							if(data.data.status) {
								$scope.fingerPrintData = data.data.data[0];
								
								var myItems = [];
								var items = webEvent.data;
								for (var i = 0; i < items.length; i++) {
									  if($scope.fingerPrintData.anonymousUserID == items[i].anonymousVisitorID){
										  items[i].timeStamp = moment(new Date(Number(items[i].timeStamp))).format("LLL");
										  myItems.push(items[i]);	 
									 }
							    }
								$scope.profileInfiniteScroll.items = myItems.concat($scope.profileInfiniteScroll.items);
								$scope.$apply();
							}
						});
						
					}
				});
			};
			
			if(typeof FingerPrint !== 'undefined'){
				$scope.init();
			}
			
			
			EventDispatcher.register('on-fingerprint-loaded', function(param){
				$scope.init();
			});
			
			
		});

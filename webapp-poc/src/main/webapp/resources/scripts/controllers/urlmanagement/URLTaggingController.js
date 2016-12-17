'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:URLTaggingController
 * @description # URLTaggingController Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp').controller(
		'URLTaggingController',
		function($rootScope, $scope, $q,  $timeout, $mdDialog, $mdMedia, urlTaggingService, keyService) {
		
			
			$scope.$on('show-popover', function(event, args) {
				$scope.popOver.showPopoverManual(args.data.x, args.data.y, args.data.chip);
			});
			
			  //POPOVER
			  $scope.popOver = {
					  position: {
						left:  1200  
					  },
					  key: null,
					  items: [],
					  display: false,
					  lastKey: null,
					  showPopoverManual: function(x, y, chip) {
						   if(chip.key != $scope.lastKey){
							   $scope.lastKey = chip.key;
							   keyService.getAll(chip.key, '', '').then(function(data){
								   if(data.data.status){
									   $scope.valueChip.autoComplete.items = data.data.data[0].values;
								   }
							   });
						   }
						   x += 50;
						   $scope.popOver.chip = angular.copy(chip);
						   chip.values = chip.values? chip.values : [];
						   $scope.popOver.items = chip.values;
					       $scope.popOver.position.left = x + 'px';
					       $scope.popOver.position.top = y+ 'px';
					       $scope.popOver.display = true;
					       $scope.popOver.hover = true;
					  } ,
					  showPopover: function(mouseEvent, chip, keys) {
						   for(var index in keys){
							   var item = keys[index];
							   var currChipID = $scope.keyChip.getChipID(chip);
								var chipID = $scope.keyChip.getChipID(item);
							   if(chipID == currChipID) {
								   if(item.key != $scope.lastKey){
									   $scope.lastKey = item.key;
									   keyService.getAll(item.key, '', '').then(function(data){
										   if(data.data.status){
											   $scope.valueChip.autoComplete.items = data.data.data[0].values;
											   $scope.valueChip.options.autofocus = true;
										   }
									   });
								   }
								   $scope.popOver.chip = angular.copy(chip);
								   keys[index].values = item.values? item.values : [];
								   $scope.popOver.items = keys[index].values;
								   
							   }
						   }
					    if (!mouseEvent)
					      {
					        mouseEvent = window.event;
					      }
					      $scope.popOver.position.left = mouseEvent.pageX + 'px';
					      $scope.popOver.position.top = mouseEvent.pageY+ 'px';
					      $scope.popOver.display = true;
					      $scope.popOver.hover = false;
					  }  
			  }
			  
		    
		    
		    
			 
			$scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
			$scope.tagModal = function(ev) {
			    var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
			    $mdDialog.show({
			      controller: DialogController,
			      templateUrl: 'resources/views/urltagging/taggingdialog.tmpl.html',
			      parent: angular.element(document.body),
			      targetEvent: ev,
			      clickOutsideToClose:true,
			      fullscreen: useFullScreen
			    })
			    .then(function(answer) {
			      $scope.status = 'You said the information was "' + answer + '".';
			    }, function() {
			      $scope.status = 'You cancelled the dialog.';
			    });
			    $scope.$watch(function() {
			      return $mdMedia('xs') || $mdMedia('sm');
			    }, function(wantsFullScreen) {
			      $scope.customFullscreen = (wantsFullScreen === true);
			    });
			  };
			  
			 //UUID 
			  $scope.uuid =  function() {
				            function _p8(s) {
				                var p = (Math.random().toString(5)+"000000000").substr(2,8);
				                return s ? "-" + p.substr(0,4) + "-" + p.substr(4,4) : p ;
				            }
				            return _p8() ;
			  }
			  
			//KEY CHIPS  
			$scope.keyChip = {
					autoComplete: {
						allKeys: []
					},
					onTransformChip: function(chip, selected){
						var copy = angular.copy(chip);
						copy.values = [];
						if(selected.length > 0){
							var proceed = true;
							for(var index in selected){
								var select = selected[index];
								if(proceed){
									copy.popupDisplay = true;
									proceed = false;
								} else {
									copy.popupDisplay = false;
								}
								$scope.keyChip.fillUniqueObject(copy, select.keys);
							
							}
						} else {
							copy.popupDisplay = true;
							return copy;
						}
						
						return null;
					},
					onRemoveChip: function(chip, selected){
						chip = angular.copy(chip);
						if(selected.length > 0){
							angular.forEach(selected,function(select,selectIndex){
								for (var i = select.keys.length - 1; i >= 0; i--) {
									var item = select.keys[i];
									var currChipID = $scope.keyChip.getChipID(chip);
									var chipID = $scope.keyChip.getChipID(item);
									if(currChipID == chipID){
										select.keys.splice(select.keys.indexOf(item), 1);  
									}
								}
							});
						}
					},
					fillUniqueObject: function(chip, items){
						items.push(angular.copy(chip));
					},
					getValueStr: function(chip){
						var values = [];
						for(var index in chip.values){
							values.push(chip.values[index].value);
						}
						return values.join();
					},
					getChipID: function(chip){
						if(chip.values != null){
							return chip.key +"-"+$scope.keyChip.getValueStr(chip);
						} else {
							return chip.key;
						}
					}
			};
			
			keyService.getAll("","", "").then(function(data){
		    	if(data.data.status){
		    		$scope.keyChip.autoComplete.allKeys =  data.data.data;
		    	}
		    });
			
			//VALUE CHIPS  
			$scope.valueChip = {
					options: {
						autofocus: true
					},
					autoComplete: {
						items: []
					},
					onTransformChip: function(chip, popChip, selected){
						var chipID = $scope.keyChip.getChipID(popChip);
						if(typeof chip === 'string') {
							chip = {key: popChip.key, value: chip};
						}
						var copy = angular.copy(chip);
						if(selected.length > 0){
							for(var index in selected){
								var select = selected[index];
								for(var keyIndex in select.keys){
									var item = select.keys[keyIndex];
									var currChipID = $scope.keyChip.getChipID(item);
									if(chipID == currChipID){
										item.values = item.values? item.values: [];
										$scope.valueChip.fillUniqueObject(copy, item.values);
										$scope.popOver.chip = angular.copy(item);
									}
								}
							}
						}
						return copy;
					},
					onRemoveChip: function(chip, popChip, selected){
						if(selected.length > 0){
							var chipID = $scope.keyChip.getChipID(popChip);
							for(var index in selected){
								var select = selected[index];
								for(var keyIndex in select.keys){
									var item = select.keys[keyIndex];
									var currChipID = $scope.keyChip.getChipID(item);
									if(chipID == currChipID){
										$scope.valueChip.removeObject(chip, item.values);
										$scope.popOver.chip = angular.copy(item);
									}
								}
							}
						}
					},
					removeObject: function(chip, values){
						for(var index in values){
							var item = values[index];
							if(chip.value == item.value){
								values.splice(index, 1);   
							}
						}
					},
					fillUniqueObject: function(chip, items){
						items.push(angular.copy(chip));
					},
					getChipID: function(chip){
						if(chip.values != null){
							var values = [];
							for(var index in chip.values){
								values.push(item[index].value);
							}
							return chip.key +"-"+values.join();
						} else {
							return chip.key;
						}
					}
			};
			$scope.$watch('urlTable.searchValue', function (newValue, oldValue) {
				if($scope.searchForm.searchValue.$valid){
					urlTaggingService.getAllURLs($scope.urlTable.searchValue, $scope.urlTable.query.page, $scope.urlTable.query.limit).then(function(data){
						if(data.data.status){
							$scope.urlTable.items = data.data;
							
						}
					});
				}
			  });
			
			$scope.urlTable = {
					tag: function(selected){
						urlTaggingService.save(selected).then(function(data){
							if(data.data.status){
								$rootScope.toast("Success!", "md-primary");
							}
						});
					},
					searchValue: null,
					search: function(searchForm){
						if(searchForm.searchValue.$valid){
							urlTaggingService.getAllURLs($scope.urlTable.searchValue, $scope.urlTable.query.page, $scope.urlTable.query.limit).then(function(data){
								if(data.data.status){
									$scope.urlTable.items = data.data;
									
								}
							});
						}
					},
					items: [],
					query: {
							    order: 'name',
							    limit: 5,
							    page: 1
				    },
				    onPaginate: function(page, limit) {
						urlTaggingService.getAllURLs($scope.urlTable.searchValue, $scope.urlTable.query.page, $scope.urlTable.query.limit).then(function(data){
							if(data.data.status){
								$scope.urlTable.items = data.data;
							}
						});
					},
				    onReorder: function(order) {

					},
					selected: [],
					onSelect: function(item){
						
					},
					onDeselect: function(item){
						
					},
					limitOptions: [5, 10, 15, {
					    label: 'All',
					    value: function () {
					      return $scope.urlTable.items ? $scope.urlTable.items.count : 0;
					    }
				     }],
					options: {
						    rowSelection: true,
						    multiSelect: true,
						    autoSelect: true,
						    decapitate: false,
						    largeEditDialog: false,
						    boundaryLinks: false,
						    limitSelect: true,
						    pageSelect: true
						  }	
			}


					
		});

function DialogController($scope, $mdDialog) {
  $scope.hide = function() {
    $mdDialog.hide();
  };
  $scope.cancel = function() {
    $mdDialog.cancel();
  };
  $scope.answer = function(answer) {
    $mdDialog.hide(answer);
  };
}
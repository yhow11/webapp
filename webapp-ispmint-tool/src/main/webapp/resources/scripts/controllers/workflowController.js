'use strict';
/**
 * @ngdoc function
 * @name ispMintApp.controller:WorkFlowController
 * @description
 * # MainCtrl
 * Controller of the ispMintApp
 */
angular.module('ispMintApp')
  .controller('WorkFlowController', function($timeout, $rootScope, $scope, $stateParams, workflowService, organizationService) {
	  var self = this;
	    var pendingSearch, cancelSearch = angular.noop;
	    var cachedQuery, lastSearch;
	    
	    $scope.webapplication = {};
	    $scope.webapplication.data = [{
					    		name: 'Podio',
					    		email: 'Podio.com'
					        },
					        {
					        	name: 'Gmail',
					        	email: 'Gmail.com'
					        }
					        ];
	    $scope.webapplication.selected = [];
	    $scope.webapplication.filterSelected = true;
	    $scope.webapplication.filter =  function createFilterFor(query) {
	        var lowercaseQuery = angular.lowercase(query);
	        return function filterFn(contact) {
	          return (contact.name.toLowerCase().indexOf(lowercaseQuery) != -1);;
	        };
	      };
	    $scope.webapplication.query = function query(criteria) {
  		      return $scope.webapplication.data.filter($scope.webapplication.filter(criteria));
		    	
	    };
	    
	    
//	    workflowService.getAll().then(function(data){
//    		$rootScope.displayError(angular.toJson(data));
//    	});
//	    
	    
	    
	    $scope.organization = {};
	    organizationService.getAll().then(function(data){
	    	if(data.status) {
	    		$scope.organization.data = data.data.data;
	    	}
    	});
	    $scope.organization.data = [];
	    $scope.organization.selected = [];
	    $scope.organization.filterSelected = true;
	    $scope.organization.filter =  function filterOrg(query) {
	        var lowercaseQuery = angular.lowercase(query);
	        return function filterFn(contact) {
	          return (contact.name.toLowerCase().indexOf(lowercaseQuery) != -1);;
	        };
	      };
	  
	    $scope.organization.query = function query(criteria) {
  		      return $scope.organization.data.filter( $scope.organization.filter(criteria));
		    	
	    };
	    
	    /**
	     * Search for contacts; use a random delay to simulate a remote call
	     */
	    
	    function createFilterFor(query) {
	        var lowercaseQuery = angular.lowercase(query);
	        return function filterFn(contact) {
	          return (contact.name.toLowerCase().indexOf(lowercaseQuery) != -1);;
	        };
	      }
	    
	    
	    var tabs = [
	                { title: 'Step 1', content: "Choose your web application.", disable:false, template: 'resources/views/tabs/step1.html'},
	                { title: 'Two', content: "You can swipe left and right on a mobile device to change tabs.", disable:true, template: 'resources/views/tabs/step1.html'},
	                { title: 'Three', content: "You can bind the selected tab via the selected attribute on the md-tabs element.", disable:true, template: 'resources/views/tabs/step1.html'},
	                { title: 'Four', content: "If you set the selected tab binding to -1, it will leave no tab selected.", disable:true, template: 'resources/views/tabs/step1.html'},
	                { title: 'Five', content: "If you remove a tab, it will try to select a new one.", disable:true, template: 'resources/views/tabs/sidepanel.html'},
	                { title: 'Six', content: "There's an ink bar that follows the selected tab, you can turn it off if you want.", disable:true, template: 'resources/views/tabs/sidepanel.html'},
	                { title: 'Seven', content: "If you set ng-disabled on a tab, it becomes unselectable. If the currently selected tab becomes disabled, it will try to select the next tab.", disable:true, template: 'resources/views/tabs/sidepanel.html'},
	                { title: 'Eight', content: "If you look at the source, you're using tabs to look at a demo for tabs. Recursion!", disable:true, template: 'resources/views/tabs/sidepanel.html'},
	                { title: 'Nine', content: "If you set md-theme=\"green\" on the md-tabs element, you'll get green tabs.", disable:true, template: 'resources/views/tabs/sidepanel.html'},
	                { title: 'Ten', content: "If you're still reading this, you should just go check out the API docs for tabs!", disable:true, template: 'resources/views/tabs/sidepanel.html'}
	              ],
	              selected = null,
	              previous = null;
	    
	    $scope.tabs = tabs;
	    	
	    $scope.selectedIndex = 2;
	    $scope.next = function() {
	    	$scope.tabs[$scope.selectedIndex+1].disable=false;
	    	if($scope.selectedIndex <= $scope.tabs.length) {
	    		 $timeout(function() {
	    			 $scope.selectedIndex +=1;
	    		    }, 200);
	    		
	    	} else {
	    		$scope.selectedIndex = 0;
	    	}
	    	
	    };
	  
  });

'use strict';
/**
 * @ngdoc overview
 * @name ispMintApp
 * @description
 * # ispMintApp
 *
 * Main module of the application.
 */

angular
  .module('ispMintApp', [    'oc.lazyLoad',
                             'ui.router',
                             'ngStorage',
                             'md.data.table',
                             'ngMaterial',
                             'ngMessages',
'ngMdIcons',
'highcharts-ng'
  ])
  .config(['$mdThemingProvider', '$stateProvider','$urlRouterProvider','$ocLazyLoadProvider', '$httpProvider',function ($mdThemingProvider, $stateProvider,$urlRouterProvider,$ocLazyLoadProvider, $httpProvider) {

	  $mdThemingProvider.theme('default')
	    .primaryPalette('green')
	        .accentPalette('red')
	    .backgroundPalette('grey');
	  
    $ocLazyLoadProvider.config({
      debug:false,
      events:true,
    });

//    $urlRouterProvider.otherwise('/home');

    $stateProvider
      .state('home', {
        url:'/home',
        controller: 'HomeController',
        templateUrl: 'resources/views/home.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'ispMintApp',
              files:[
              'resources/scripts/controllers/homeController.js'
              ]
            })
          }
        }
        
    }) .state('login', {
        url:'/login',
        controller: 'LoginController',
        templateUrl: 'resources/views/login.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'ispMintApp',
              files:[
              'resources/scripts/services/authenticationService.js',
              'resources/scripts/controllers/loginController.js'
              ]
            })
          }
        }
        
    }) .state('workflow', {
        url:'/workflow',
        controller: 'WorkFlowController',
        templateUrl: 'resources/views/workflow.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'ispMintApp',
              files:[
               'resources/scripts/services/organizationService.js',
              'resources/scripts/services/workflowService.js',
              'resources/scripts/controllers/workflowController.js'
              ]
            })
          }
        }
        
    }).state('trigger', {
        url:'/trigger',
        params: { param: null},
        controller: 'TriggerController',
        templateUrl: 'resources/views/trigger.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'ispMintApp',
              files:[
              'resources/scripts/controllers/triggerController.js',
              
              ]
            })
          }
        }
        
    }).state('membership', {
        url:'/membership/:id',
        controller: 'MembershipController',
        templateUrl: 'resources/views/membership.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'ispMintApp',
              files:[
              'resources/scripts/services/memberService.js',
              'resources/scripts/controllers/membershipController.js',
              
              ]
            })
          }
        }
        
    }).state('test', {
        url:'/test',
        controller: 'TestController',
        templateUrl: 'resources/views/test.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'ispMintApp',
              files:[
              'resources/scripts/services/memberService.js',
              'resources/scripts/controllers/testController.js',
              
              ]
            })
          }
        }
        
    });
//      .state('dashboard.home',{
//        url:'/home',
//        controller: 'MainCtrl',
//        params: {
//            org: null
//          },
//        templateUrl:'resources/views/dashboard/home.html',
//        resolve: {
//          loadMyFiles:function($ocLazyLoad) {
//            return $ocLazyLoad.load({
//              name:'sbAdminApp',
//              files:[
//              'resources/scripts/controllers/homeController.js',
//              'resources/scripts/directives/timeline/timeline.js',
//              'resources/scripts/directives/notifications/notifications.js',
//              'resources/scripts/directives/chat/chat.js',
//              'resources/scripts/directives/dashboard/stats/stats.js'
//              ]
//            })
//          }
//        }
//      })
//      .state('dashboard.form',{
//        templateUrl:'resources/views/form.html',
//        url:'/form'
//    })
//      .state('dashboard.blank',{
//        templateUrl:'resources/views/pages/blank.html',
//        url:'/blank'
//    })
//      .state('login',{
//        templateUrl:'resources/views/pages/login.html',
//        url:'/login',
//        controller:'LoginCtrl',
//        resolve: {
//            loadMyFile:function($ocLazyLoad) {
//          return $ocLazyLoad.load({
//              name:'sbAdminApp',
//              files:['resources/scripts/controllers/loginController.js',
//                     'resources/scripts/services/authenticationService.js',
//                     'resources/scripts/services/userService.js']
//          })
//        }
//      }
//    })
//      .state('dashboard.chart',{
//        templateUrl:'resources/views/chart.html',
//        url:'/chart',
//        controller:'ChartCtrl',
//        resolve: {
//          loadMyFile:function($ocLazyLoad) {
//            return $ocLazyLoad.load({
//              name:'chart.js',
//              files:[
//                'resources/bower_components/angular-chart.js/dist/angular-chart.min.js',
//                'resources/bower_components/angular-chart.js/dist/angular-chart.css'
//              ]
//            }),
//            $ocLazyLoad.load({
//                name:'sbAdminApp',
//                files:['resources/scripts/controllers/chartContoller.js']
//            })
//          }
//        }
//    })
//      .state('dashboard.spaces',{
//        templateUrl:'resources/views/spaces.html',
//        url:'/spaces',
//        controller: 'SpacesCtrl',
//        params: {
//            space: null
//          },
//        resolve: {
//	            loadMyFile:function($ocLazyLoad) {
//	          return $ocLazyLoad.load({
//	              name:'sbAdminApp',
//	              files:['resources/scripts/controllers/spacesController.js']
//	          }),
//	          $ocLazyLoad.load(
//              {
//                name:'datatables',
//                files:['resources/bower_components/datatables/datatables.min.js',
//                       'resources/styles/datatables.min.css',
//                       'resources/bower_components/angular-datatables/angular-datatables.min.js']
//              }),
//              $ocLazyLoad.load(
//              {
//                name:'datatables.bootstrap',
//                files:['resources/bower_components/angular-datatables/angular-datatables.bootstrap.min.js',
//                       'resources/styles/datatables.bootstrap.min.css']
//              })
//              
//	        }
//        }
//    }).state('dashboard.apps',{
//        templateUrl:'resources/views/apps.html',
//        url:'/apps',
//        params: {
//            application: null
//          },
//        resolve: {
//	            loadMyFile:function($ocLazyLoad) {
//	          return $ocLazyLoad.load({
//	              name:'sbAdminApp',
//	              files:['resources/scripts/controllers/appsController.js',
//	                     'resources/scripts/controllers/tableController.js']
//	          }),
//	          $ocLazyLoad.load(
//              {
//                name:'datatables',
//                files:['resources/bower_components/datatables/datatables.min.js',
//                       'resources/styles/datatables.min.css',
//                       'resources/bower_components/angular-datatables/angular-datatables.min.js']
//              }),
//              $ocLazyLoad.load(
//                      {
//                        name:'datatables.fixedcolumns',
//                        files:['resources/bower_components/datatables/dataTables.fixedColumns.min.js',
//                               'resources/styles/fixedColumns.dataTables.min.css',
//                               'resources/bower_components/angular-datatables/angular-datatables.fixedcolumns.min.js']
//                      }),
//              $ocLazyLoad.load(
//              {
//                name:'datatables.bootstrap',
//                files:['resources/bower_components/angular-datatables/angular-datatables.bootstrap.min.js',
//                       'resources/styles/datatables.bootstrap.min.css']
//              })
//              
//	        }
//        }
//    })
//      .state('dashboard.panels-wells',{
//          templateUrl:'resources/views/ui-elements/panels-wells.html',
//          url:'/panels-wells'
//      })
//      .state('dashboard.buttons',{
//        templateUrl:'resources/views/ui-elements/buttons.html',
//        url:'/buttons'
//    })
//      .state('dashboard.notifications',{
//        templateUrl:'resources/views/ui-elements/notifications.html',
//        url:'/notifications'
//    })
//      .state('dashboard.typography',{
//       templateUrl:'resources/views/ui-elements/typography.html',
//       url:'/typography'
//   })
//      .state('dashboard.icons',{
//       templateUrl:'resources/views/ui-elements/icons.html',
//       url:'/icons'
//   })
//      .state('dashboard.grid',{
//       templateUrl:'resources/views/ui-elements/grid.html',
//       url:'/grid'
//   })
  }]).run(function ($mdToast, $interval, $rootScope, $state, $location, $localStorage, $http, $mdSidenav, $log) {
	  $mdSidenav("left").then(function(instance) {
		  $log.debug(instance);
	  	});
	  $rootScope.options = {
		      from: 'fa-bars',
		      to: 'fa-arrow-left',
		      animation: 'tada'
	  };
	  $rootScope.iconate = function(option){
		  iconate(document.getElementById('icon'), option);
		  $rootScope.options = {
			      from: $rootScope.options.to,
			      to: $rootScope.options.from,
			      animation: 'tada'
		  };
		  $mdSidenav("left")
		   .toggle();
	  }
	  $rootScope.goTo = function(state, param) {
			$state.go(state, {
				param : param
			});
		};
      // keep user logged in after page refresh
      $rootScope.currentUser = $localStorage.currentUser || null;
      if ($rootScope.currentUser) {
          $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser.authdata; // jshint ignore:line
      }
      $rootScope.$on('$locationChangeStart', function (event, next, current) {

          var loggedIn = $rootScope.currentUser;
          if (!next.contains('/login') && !loggedIn) {
        	  $location.path('/login');
          }
      });
      
      $rootScope.showLoading = true;
      $rootScope.isLoading = function () {
          return $http.pendingRequests.length > 0;
      };
      $rootScope.$watch($rootScope.isLoading, function (v)
                {
                    if(v){
                    	
                    	$rootScope.loadingInterval =  $interval(function() {
                			$rootScope.determinateValue += 1;
                		    }, 100);
                    	$rootScope.showLoading = true;
                    }else{
                    	$interval.cancel($rootScope.loadingInterval);
                    	$rootScope.determinateValue = 0;
                    	$rootScope.showLoading = false;
                    }
                });
      $rootScope.displayError = function(message) {
    	    $mdToast.showSimple(message).textContent(message)
	        .position("top right")
	        .hideDelay(3000);
    	  };
	
  });
    

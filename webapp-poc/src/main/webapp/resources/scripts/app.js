'use strict';
/**
 * @ngdoc overview
 * @name fingerPrintApp
 * @description # fingerPrintApp
 * 
 * Main module of the application.
 */
var getUrl = window.location;
var baseUrl = getUrl.protocol + "//" + getUrl.host + "/"
		+ getUrl.pathname.split('/')[1];
angular
		.module(
				'fingerPrintApp',
				[ 'oc.lazyLoad', 'ui.router', 'ngStorage',
						'md.data.table', 'ngMaterial', 'ngMessages',
						'ngMdIcons', 'infinite-scroll' ])
		.config(
				[
						'$mdThemingProvider',
						'$stateProvider',
						'$urlRouterProvider',
						'$ocLazyLoadProvider',
						'$httpProvider',
						function($mdThemingProvider, $stateProvider,
								$urlRouterProvider, $ocLazyLoadProvider,
								$httpProvider) {

							$mdThemingProvider.theme('tooltiptheme').primaryPalette(
							'red').dark().foregroundPalette['3'] = "white";
							$mdThemingProvider.theme('default').primaryPalette(
							'blue');
							
					        

							
							$mdThemingProvider.setDefaultTheme('default');
							
							$ocLazyLoadProvider.config({
								debug : false,
								events : true,
							});

							$urlRouterProvider.otherwise('/home');

							$stateProvider
									
									.state(
											'test',
											{
												url : '/test',
												controller : 'TestController',
												templateUrl : 'resources/views/test.html',
												resolve : {
													loadMyFiles : function(
															$ocLazyLoad) {
														return $ocLazyLoad
																.load({
																	name : 'fingerPrintApp',
																	files : [
																			'resources/scripts/services/eventService.js',
																			'resources/scripts/controllers/testController.js',

																	]
																})
													}
												}

											})
									.state(
									'profile',
									{
										url : '/profile',
										controller : 'ProfileController',
										templateUrl : 'resources/views/profile.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
																	'resources/scripts/services/eventService.js',
																	'resources/scripts/services/sessionService.js',
																	'resources/scripts/services/profileInfiniteScroll.js',
																	'resources/scripts/controllers/profileController.js',

															]
														})
											}
										}

									})
									.state(
									'keymanagement',
									{
										abstract: true,
										template : '<ui-view  ></ui-view>',
										url : '/keymanagement'

									})
									.state(
									'keymanagement.addnew',
									{
										url : '/keymanagement/addnew',
										controller : 'AddNewController',
										templateUrl : 'resources/views/keymanagement/addnew.html',
										params: {
											param: null
										},
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
													         		'resources/scripts/services/keymanagement/keymanagementService.js',
													         		'resources/scripts/directives/keymanagement/validations/validvalueDirective.js',
																	'resources/scripts/directives/keymanagement/validations/validkeyDirective.js',
																	'resources/scripts/controllers/keymanagement/addnewController.js'

															]
														})
											}
										}

									})
									.state(
									'keymanagement.view',
									{
										url : '/keymanagement/view',
										controller : 'ViewController',
										templateUrl : 'resources/views/keymanagement/view.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/scripts/services/keymanagement/keymanagementService.js',
																	'resources/scripts/services/keymanagement/keymanagementInfiniteScroll.js',
																	'resources/scripts/controllers/keymanagement/viewController.js',

															]
														})
											}
										}

									})
									.state(
									'urlmanagement',
									{
										abstract: true,
										template : '<ui-view  ></ui-view>',
										url : '/urlmanagement'

									})
									.state(
									'urlmanagement.urltagging',
									{
										url : 'urlmanagement/urltagging',
										controller : 'URLTaggingController',
										templateUrl : 'resources/views/urlmanagement/tagging.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/scripts/directives/urlmanagement/mdAutocompleteDirective.js',
														         	'resources/scripts/directives/urlmanagement/mdChipDirective.js',
														         	'resources/scripts/services/keymanagement/keymanagementService.js',
														         	'resources/scripts/services/urlmanagement/URLTaggingService.js',
																	'resources/scripts/controllers/urlmanagement/URLTaggingController.js',

															]
														})
											}
										}

									})
									.state(
									'urlmanagement.urlimport',
									{
										url : 'urlmanagement/urlimport',
										controller : 'ImportURLController',
										templateUrl : 'resources/views/urlmanagement/import.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/scripts/services/urlmanagement/importURLService.js',
															         'resources/scripts/controllers/urlmanagement/importURLController.js',
															]
														})
											}
										}

									})
									.state(
											'logs',
											{
												url : '/logs',
												controller : 'LogsController',
												templateUrl : 'resources/views/logs.html',
												resolve : {
													loadMyFiles : function(
															$ocLazyLoad) {
														return $ocLazyLoad
																.load({
																	name : 'fingerPrintApp',
																	files : [
																			'resources/scripts/services/logs/logsService.js',
																			'resources/scripts/services/logs/logsInfiniteScroll.js',
																			'resources/scripts/controllers/logsController.js',

																	]
																})
													}
												}

											});
							// .state('dashboard.home',{
							// url:'/home',
							// controller: 'MainCtrl',
							// params: {
							// org: null
							// },
							// templateUrl:'resources/views/dashboard/home.html',
							// resolve: {
							// loadMyFiles:function($ocLazyLoad) {
							// return $ocLazyLoad.load({
							// name:'sbAdminApp',
							// files:[
							// 'resources/scripts/controllers/homeController.js',
							// 'resources/scripts/directives/timeline/timeline.js',
							// 'resources/scripts/directives/notifications/notifications.js',
							// 'resources/scripts/directives/chat/chat.js',
							// 'resources/scripts/directives/dashboard/stats/stats.js'
							// ]
							// })
							// }
							// }
							// })
							// .state('dashboard.form',{
							// templateUrl:'resources/views/form.html',
							// url:'/form'
							// })
							// .state('dashboard.blank',{
							// templateUrl:'resources/views/pages/blank.html',
							// url:'/blank'
							// })
							// .state('login',{
							// templateUrl:'resources/views/pages/login.html',
							// url:'/login',
							// controller:'LoginCtrl',
							// resolve: {
							// loadMyFile:function($ocLazyLoad) {
							// return $ocLazyLoad.load({
							// name:'sbAdminApp',
							// files:['resources/scripts/controllers/loginController.js',
							// 'resources/scripts/services/authenticationService.js',
							// 'resources/scripts/services/userService.js']
							// })
							// }
							// }
							// })
							// .state('dashboard.chart',{
							// templateUrl:'resources/views/chart.html',
							// url:'/chart',
							// controller:'ChartCtrl',
							// resolve: {
							// loadMyFile:function($ocLazyLoad) {
							// return $ocLazyLoad.load({
							// name:'chart.js',
							// files:[
							// 'resources/bower_components/angular-chart.js/dist/angular-chart.min.js',
							// 'resources/bower_components/angular-chart.js/dist/angular-chart.css'
							// ]
							// }),
							// $ocLazyLoad.load({
							// name:'sbAdminApp',
							// files:['resources/scripts/controllers/chartContoller.js']
							// })
							// }
							// }
							// })
							// .state('dashboard.spaces',{
							// templateUrl:'resources/views/spaces.html',
							// url:'/spaces',
							// controller: 'SpacesCtrl',
							// params: {
							// space: null
							// },
							// resolve: {
							// loadMyFile:function($ocLazyLoad) {
							// return $ocLazyLoad.load({
							// name:'sbAdminApp',
							// files:['resources/scripts/controllers/spacesController.js']
							// }),
							// $ocLazyLoad.load(
							// {
							// name:'datatables',
							// files:['resources/bower_components/datatables/datatables.min.js',
							// 'resources/styles/datatables.min.css',
							// 'resources/bower_components/angular-datatables/angular-datatables.min.js']
							// }),
							// $ocLazyLoad.load(
							// {
							// name:'datatables.bootstrap',
							// files:['resources/bower_components/angular-datatables/angular-datatables.bootstrap.min.js',
							// 'resources/styles/datatables.bootstrap.min.css']
							// })
							//              
							// }
							// }
							// }).state('dashboard.apps',{
							// templateUrl:'resources/views/apps.html',
							// url:'/apps',
							// params: {
							// application: null
							// },
							// resolve: {
							// loadMyFile:function($ocLazyLoad) {
							// return $ocLazyLoad.load({
							// name:'sbAdminApp',
							// files:['resources/scripts/controllers/appsController.js',
							// 'resources/scripts/controllers/tableController.js']
							// }),
							// $ocLazyLoad.load(
							// {
							// name:'datatables',
							// files:['resources/bower_components/datatables/datatables.min.js',
							// 'resources/styles/datatables.min.css',
							// 'resources/bower_components/angular-datatables/angular-datatables.min.js']
							// }),
							// $ocLazyLoad.load(
							// {
							// name:'datatables.fixedcolumns',
							// files:['resources/bower_components/datatables/dataTables.fixedColumns.min.js',
							// 'resources/styles/fixedColumns.dataTables.min.css',
							// 'resources/bower_components/angular-datatables/angular-datatables.fixedcolumns.min.js']
							// }),
							// $ocLazyLoad.load(
							// {
							// name:'datatables.bootstrap',
							// files:['resources/bower_components/angular-datatables/angular-datatables.bootstrap.min.js',
							// 'resources/styles/datatables.bootstrap.min.css']
							// })
							//              
							// }
							// }
							// })
							// .state('dashboard.panels-wells',{
							// templateUrl:'resources/views/ui-elements/panels-wells.html',
							// url:'/panels-wells'
							// })
							// .state('dashboard.buttons',{
							// templateUrl:'resources/views/ui-elements/buttons.html',
							// url:'/buttons'
							// })
							// .state('dashboard.notifications',{
							// templateUrl:'resources/views/ui-elements/notifications.html',
							// url:'/notifications'
							// })
							// .state('dashboard.typography',{
							// templateUrl:'resources/views/ui-elements/typography.html',
							// url:'/typography'
							// })
							// .state('dashboard.icons',{
							// templateUrl:'resources/views/ui-elements/icons.html',
							// url:'/icons'
							// })
							// .state('dashboard.grid',{
							// templateUrl:'resources/views/ui-elements/grid.html',
							// url:'/grid'
							// })
						} ])
		.run(
				function($rootScope, $state, $location, $localStorage, $http,
						$mdSidenav, $log, $interval, $mdToast) {
					$mdSidenav("left").then(function(instance) {
						$log.debug(instance);
					});
					$rootScope.options = {
						from : 'fa-bars',
						to : 'fa-arrow-left',
						animation : 'tada'
					};
					$rootScope.iconate = function(option) {
						iconate(document.getElementById('icon'), option);
						$rootScope.options = {
							from : $rootScope.options.to,
							to : $rootScope.options.from,
							animation : 'tada'
						};
						$mdSidenav("left").toggle();
					}
					$rootScope.goTo = function(state, param) {
						$state.go(state, {
							param : param
						});
					};
					$rootScope.showLoading = false;
					$rootScope.isLoading = function() {
						return $http.pendingRequests.length > 0;
					};
					$rootScope.$watch($rootScope.isLoading, function(v) {
						if (v) {

							$rootScope.loadingInterval = $interval(function() {
								$rootScope.determinateValue += 1;
							}, 100);
							$rootScope.showLoading = true;
						} else {
							$interval.cancel($rootScope.loadingInterval);
							$rootScope.determinateValue = 0;
							$rootScope.showLoading = false;
						}
					});
					$rootScope.toast = function(message, color) {
						var mdToast = $mdToast.simple()
					      .textContent(message)
								.position("top right").hideDelay(3000);
						$mdToast.show(mdToast);
					};
					$rootScope.stompReceivers = null;
					connect();
					function connect() {
						var socket = new SockJS('http://54.213.0.17:8191/webapp-poc/notify');
						var oldInit = window.onbeforeunload;
						window.onbeforeunload = function (event) {
							if (oldInit) oldInit(event);
					    	socket.close();
					    	console.log("socket closed.");
						}
						$rootScope.stompReceivers = Stomp.over(socket);
						$rootScope.stompReceivers
								.connect(
										{},
										function(frame) {
											
											$rootScope.stompReceivers.subscribe('/event/notifyReceivers', function(
													data) {
												var response = JSON.parse(JSON.stringify(data.body));
												$rootScope.$emit('notifyReceivers', {data: JSON.parse(response)});
												
												
											});
										});
					}
					$rootScope.$on("$routeChangeStart", function(event, next,
							current) {
//						connect();
					});
				});


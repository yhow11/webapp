'use strict';
/**
 * @ngdoc overview
 * @name fingerPrintApp
 * @description # fingerPrintApp
 * 
 * Main module of the application.
 */
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
																	'resources/scripts/services/anonymousVisitorService.js',
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
										controller : 'URLImportController',
										templateUrl : 'resources/views/urlmanagement/import.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/scripts/services/urlmanagement/URLImportService.js',
															         'resources/scripts/controllers/urlmanagement/URLImportController.js',
															]
														})
											}
										}

									})
									.state(
									'metricmanagement',
									{
										abstract: true,
										template : '<ui-view  ></ui-view>',
										url : '/metricmanagement'

									})
									.state(
									'metricmanagement.addnew',
									{
										url : '/metricmanagement/addnew',
										controller : 'AddNewController',
										templateUrl : 'resources/views/metricmanagement/addnew.html',
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
														         	'resources/scripts/services/metricmanagement/metricService.js',
														         	'resources/scripts/services/metricmanagement/metricTypeService.js',
																	'resources/scripts/controllers/metricmanagement/addnewController.js'

															]
														})
											}
										}

									})
									.state(
									'metricmanagement.view',
									{
										url : '/metricmanagement/view',
										controller : 'ViewController',
										templateUrl : 'resources/views/metricmanagement/view.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/scripts/services/metricmanagement/metricService.js',
														         	'resources/scripts/services/metricmanagement/metricInfiniteScroll.js',
																	'resources/scripts/controllers/metricmanagement/viewController.js'

															]
														})
											}
										}

									})
									.state(
									'metricmanagement.summary',
									{
										url : '/metricmanagement/summary',
										controller : 'SummaryController',
										templateUrl : 'resources/views/metricmanagement/summary.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/scripts/services/metricmanagement/metricSummaryService.js',
																	'resources/scripts/controllers/metricmanagement/summaryController.js'

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
						var origin = location.protocol + "//" + location.host;
						var socket = new SockJS(origin+'/webapp-poc/send');
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
											
											$rootScope.stompReceivers.subscribe('/logs/broadcast', function(
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


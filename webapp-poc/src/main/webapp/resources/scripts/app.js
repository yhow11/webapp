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
									'keymanagement.addnewkey',
									{
										url : '/addnewkey',
										controller : 'AddNewKeyController',
										templateUrl : 'resources/key/addnew.html',
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
													         		'resources/key/keyService.js',
													         		'resources/key/validvalueDirective.js',
																	'resources/key/validkeyDirective.js',
																	'resources/key/addnewKeyController.js'

															]
														})
											}
										}

									})
									.state(
									'keymanagement.viewkey',
									{
										url : '/viewkeys',
										controller : 'ViewKeyController',
										templateUrl : 'resources/key/view.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/key/keyService.js',
																	'resources/key/viewKeyController.js',

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
														         	'resources/key/keyService.js',
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
									'metricmanagement.addnewmetric',
									{
										url : '/addnew/:id',
										controller : 'AddNewMetricController',
										templateUrl : 'resources/metric/addnew.html',
										params: {
											param: null,
											id: null
										},
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/key/keyService.js',
														         	'resources/metric/metricService.js',
														         	'resources/metric/metricTypeService.js',
																	'resources/metric/addnewMetricController.js'

															]
														})
											}
										}

									})
									.state(
									'metricmanagement.viewmetric',
									{
										url : '/view',
										controller : 'ViewMetricController',
										templateUrl : 'resources/metric/view.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/metric/metricService.js',
																	'resources/metric/viewMetricController.js'

															]
														})
											}
										}

									})
									.state(
									'metricmanagement.summarymetric',
									{
										url : '/summary',
										controller : 'SummaryMetricController',
										templateUrl : 'resources/metric/summary.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/metric/metricSummaryService.js',
																	'resources/metric/summaryMetricController.js'

															]
														})
											}
										}

									})
									.state(
									'visitormanagement',
									{
										abstract: true,
										template : '<ui-view  ></ui-view>',
										url : '/visitormanagement'

									})
									.state(
									'visitormanagement.activevisitor',
									{
										url : '/active',
										controller : 'ActiveVisitorController',
										templateUrl : 'resources/visitor/active.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/visitor/activeVisitorService.js',
																	'resources/visitor/activeVisitorController.js'

															]
														})
											}
										}

									})
									.state(
									'segmentmanagement',
									{
										abstract: true,
										template : '<ui-view  ></ui-view>',
										url : '/segmentmanagement'

									})
									.state(
									'segmentmanagement.addnewsegment',
									{
										url : '/addnew/:id',
										controller : 'AddNewSegmentController',
										templateUrl : 'resources/segment/addnew.html',
										params: {
											param: null,
											id: null
										},
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/metric/metricService.js',
														         	'resources/segment/segmentConditionService.js',
														         	'resources/segment/segmentService.js',
																	'resources/segment/addnewSegmentController.js'

															]
														})
											}
										}

									})
									.state(
									'segmentmanagement.segmentedvisitor',
									{
										url : '/segmentedvisitor/:id',
										controller : 'SegmentedVisitorController',
										templateUrl : 'resources/segment/segmentedvisitor.html',
										params: {
											param: null,
											id: null
										},
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/segment/segmentedVisitorService.js',
																	'resources/segment/segmentedvisitorController.js'

															]
														})
											}
										}

									})
									.state(
									'segmentmanagement.viewsegment',
									{
										url : '/view',
										controller : 'ViewSegmentController',
										templateUrl : 'resources/segment/view.html',
										resolve : {
											loadMyFiles : function(
													$ocLazyLoad) {
												return $ocLazyLoad
														.load({
															name : 'fingerPrintApp',
															files : [
														         	'resources/segment/segmentService.js',
																	'resources/segment/viewSegmentController.js'

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
					$rootScope.$on("$routeChangeStart", function(event, next,
							current) {
					});
				});


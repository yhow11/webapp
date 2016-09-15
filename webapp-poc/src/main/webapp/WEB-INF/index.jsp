<%@include file="/WEB-INF/taglibs.jsp"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<!-- Head -->

<!-- <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,400italic'> -->

<link rel="stylesheet"
	href="resources/vendors/angularjs/angular-material/angular-material.min.css">
<link rel="stylesheet"
	href="resources/vendors/angularjs/angular-md-data-table/md-data-table.min.css">
<link rel="stylesheet"
	href="resources/vendors/fontawesome/font-awesome.min.css">
<link rel="stylesheet"
	href="resources/vendors/angularjs/angular-chart.js/angular-chart.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="resources/styles/common.css">
<script type="text/javascript">
	/* (function() {
		var didInit = false;
		function initMunchkin() {
			if(didInit === false) {
				didInit = true;
				Munchkin.init('761-WSL-991');
			}
		}
		var s = document.createElement('script');
		s.type = 'text/javascript';
		s.async = true;
		s.src = '//munchkin.marketo.net/munchkin.js';
		s.onreadystatechange = function() {
			if (this.readyState == 'complete' || this.readyState == 'loaded') {
				initMunchkin();
			}
		};
		s.onload = initMunchkin;
		document.getElementsByTagName('head')[0].appendChild(s);
	})(); */
</script>

</head>

<body ng-app="fingerPrintApp" ng-cloak class="j-fill-grey ">


	<!-- <md-grid-list md-cols="20" md-row-height="80px">
		  <md-grid-tile md-colspan="16" md-rowspan="1" class="j-fill-green">
		  	
		  </md-grid-tile>
		  <md-grid-tile md-colspan="2" md-rowspan="1">
		  	INC Tool v1
		  </md-grid-tile>
		  <md-grid-tile md-colspan="2" md-rowspan="1" class="j-fill-red">
		  </md-grid-tile>
		</md-grid-list> -->

	<md-toolbar class="md-menu-toolbar">
	<div layout="row">
		<md-toolbar-filler layout layout-align="center center">
		<md-button ui-sref="home" class="md-icon-button md-background"
			aria-label="Home"> <md-icon
			md-svg-icon="resources/img/icons/home.svg"></md-icon> </md-button> </md-toolbar-filler>
		<div>
			<h2 class="md-toolbar-tools">Nurture Retargeting Tool</h2>
			<md-menu-bar> <md-menu>
			<button ng-click="$mdOpenMenu()">Demo</button>
			<md-menu-content> <md-menu-item> <md-button
				ui-sref="test" aria-label="Testing Section"> User
			Tracking Demo </md-button> </md-menu-item> <md-menu-divider></md-menu-divider> <md-menu-item>
			<md-button ui-sref="profile" aria-label="Profile"> User
			Profile Demo </md-button> </md-menu-item> </md-menu-content> </md-menu> <md-menu>
			<button ng-click="$mdOpenMenu()">Admin</button>
			<md-menu-content> 
				<md-menu-item> 
					<md-button ui-sref="logs" aria-label="Logs"> Logs </md-button> 
				</md-menu-item> 
				<md-menu-divider></md-menu-divider>
				<md-menu-item> 
					<md-menu> 
						<md-button ng-click="$mdOpenMenu()"> Key Management </md-button> 
						<md-menu-content>
							<md-menu-item> 
								<md-button ui-sref="keymanagement.addnew" aria-label="Profile"> Add New </md-button> 
							</md-menu-item> 
							<md-menu-divider></md-menu-divider> 
							<md-menu-item>
								<md-button ui-sref="keymanagement.view"> View </md-button> </md-menu-item> 
						</md-menu-content> 
					</md-menu> 
				</md-menu-item> 
				<md-menu-divider></md-menu-divider>
				<md-menu-item> 
					<md-menu> 
						<md-button ng-click="$mdOpenMenu()"> URL Management </md-button> 
						<md-menu-content>
							<md-menu-item> 
								<md-button ui-sref="urlmanagement.urltagging"> Tag </md-button> 
							</md-menu-item> 
							<md-menu-divider></md-menu-divider>
							<md-menu-item> 
								<md-button ui-sref="urlmanagement.urlimport"> Import </md-button> 
							</md-menu-item> 
						</md-menu-content> 
					</md-menu> 
				</md-menu-item>
				<md-menu-divider></md-menu-divider>
				<md-menu-item> 
					<md-menu> 
						<md-button ng-click="$mdOpenMenu()"> Metrics Management </md-button> 
						<md-menu-content>
							<md-menu-item> 
								<md-button ui-sref="metricmanagement.addnew"> Add New </md-button> 
							</md-menu-item> 
							<md-menu-divider></md-menu-divider>
							<md-menu-item> 
								<md-button ui-sref="metricmanagement.view"> View </md-button> 
							</md-menu-item> 
						</md-menu-content> 
					</md-menu> 
				</md-menu-item>
				</md-menu-content> 
			</md-menu> 
	</md-menu-bar>
		</div>
	</div>
	</md-toolbar>
	<md-progress-linear class="md-warn" md-mode="determinate"
		ng-show="showLoading" value="{{determinateValue}}"></md-progress-linear>
	<div ui-view></div>

	<script >
		var EventDispatcher = {
				  listeners: {},
				  register: function(evt_name, callback) {
				    if(typeof this.listeners[evt_name] == 'undefined') {
				      this.listeners[evt_name] = [];
				    }
				    this.listeners[evt_name].push(callback);
				  },
				  emit: function(evt_name, params) {
				    if(typeof this.listeners[evt_name] != 'undefined') {
				      for(var i = 0, l = this.listeners[evt_name].length; i < l; i++) {
				        this.listeners[evt_name][i].call(this, evt_name, params);
				      }
				    }
				  }
				}
		function loadScript(url, callback) {
	
			var script = document.createElement("script")
			script.type = "text/javascript";
	
			if (script.readyState) { // IE
				script.onreadystatechange = function() {
					if (script.readyState == "loaded"
							|| script.readyState == "complete") {
						script.onreadystatechange = null;
						callback();
					}
				};
			} else { // Others
				script.onload = function() {
					callback();
				};
			}
	
			script.src = url;
			document.getElementsByTagName("head")[0].appendChild(script);
		}
	
		var baseURL = 'http://poc:8191/webapp-poc/';
		var fingerprintDir = baseURL+'resources/vendors/fingerprint/';
		var cryptoDir = baseURL+'resources/vendors/crypto/';
		var websocketDir = baseURL+'resources/vendors/websocket/';
		loadScript(
				fingerprintDir+"client.min.js",
				function() {
					loadScript(
							fingerprintDir+"beaverbird.min.js",
							function() {
								loadScript(
										cryptoDir+'md5.js',
										function() {
											loadScript(
													websocketDir+"sockjs.js",
													function() {
														loadScript(
																websocketDir+"stomp.js",
																function() {
																	loadScript(
																			fingerprintDir+"fingerprint2.js",
																			function() {
																				FingerPrint.init(baseURL);
																				console.log("fingerprint tracker has been loaded");
																				EventDispatcher.emit('on-fingerprint-loaded', { });
																			});
																});
													});
										});
							});
				});

	</script>
	<!-- <script src="resources/vendors/fingerprint/client.min.js"></script> -->
	<script src="resources/vendors/jquery/jquery.min.js"></script>
	<!-- <script src="resources/vendors/fingerprint/beaverbird.min.js"></script> -->
	<script src="resources/vendors/crypto/md5.js"></script>

	<script src="resources/vendors/angularjs/angular/angular.js"></script>
	<script
		src="resources/vendors/angularjs/angular-animate/angular-animate.min.js"></script>
	<script
		src="resources/vendors/angularjs/angular-aria/angular-aria.min.js"></script>
	<script
		src="resources/vendors/angularjs/angular-material/angular-material.min.js"></script>
	<script
		src="resources/vendors/angularjs/angular-material-icons/angular-material-icons.min.js"></script>
	<script
		src="resources/vendors/angularjs/angular-route/angular-route.min.js"></script>
	<script src="resources/vendors/angularjs/angular-message/messages.js"></script>
	<script
		src="resources/vendors/angularjs/angular-ui-router/release/angular-ui-router.min.js"></script>
	<script
		src="resources/vendors/angularjs/angular-md-data-table/md-data-table.min.js"></script>
	<script
		src="resources/vendors/angularjs/angular-storage/ngStorage.min.js"></script>
	<script src="resources/vendors/oclazyload/dist/ocLazyLoad.min.js"></script>
	<script src="resources/vendors/websocket/sockjs.js"></script>
	
	
	<script src="resources/vendors/websocket/stomp.js"></script>
	<script src="resources/vendors/moment/moment.js"></script>
	<script
		src="resources/vendors/infinitescroll/ng-infinite-scroll.min.js"></script>
		
	<script src="<c:url value="resources/scripts/app.js"/>"></script>
</body>

</html>
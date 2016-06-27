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
<link rel="stylesheet"
	href="resources/vendors/iconate/iconate.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
        rel="stylesheet">
<link rel="stylesheet"
	href="resources/styles/common.css">
</head>

<body ng-app="incToolApp" layout="column" ng-cloak>


		<!-- <md-grid-list md-cols="20" md-row-height="80px">
		  <md-grid-tile md-colspan="16" md-rowspan="1" class="j-fill-green">
		  	
		  </md-grid-tile>
		  <md-grid-tile md-colspan="2" md-rowspan="1">
		  	INC Tool v1
		  </md-grid-tile>
		  <md-grid-tile md-colspan="2" md-rowspan="1" class="j-fill-red">
		  </md-grid-tile>
		</md-grid-list> -->
		 <md-toolbar class="md-primary">
		      <div class="md-toolbar-tools md-whiteframe-z1">
		      	<md-button class="md-icon-button " aria-label="Home" ng-click="iconate(options)"><i id="icon" class="fa  fa-bars fa-1x j-color-white"></i></md-button>

		        <span flex></span>
		        <md-button ui-sref="home" class="md-icon-button md-background" aria-label="Home">
		          <md-icon md-svg-icon="resources/img/icons/home.svg"></md-icon>
		        </md-button>
		        <md-button ui-sref="encoding" class="md-icon-button" aria-label="Encoding">
		          <md-icon md-svg-icon="resources/img/icons/encoding.svg"></md-icon>
		        </md-button>
		        <md-button class="md-icon-button" aria-label="Reports">
		          <md-icon md-svg-icon="resources/img/icons/reports.svg"></md-icon>
		        </md-button>
		      </div>
		    </md-toolbar>
		    <md-progress-linear class="md-warn" md-mode="determinate" ng-show="showLoading" value="{{determinateValue}}"></md-progress-linear>
		    <md-content flex layout="row" class="j-fill-grey " layout-align="center" >
		    	<div ui-view  flex-gt-sm="80" flex-sm="95" ></div>
		    </md-content>
	    <!-- <section layout="column" layout-fill>
	    	<md-toolbar flex>
		      <div class="md-toolbar-tools">
		      	<span flex></span>
		        <md-button class="md-icon-button" aria-label="Home">
		          <md-icon md-svg-icon="resources/img/icons/home.svg" style="color: white;"></md-icon>
		        </md-button>
		        <md-button class="md-icon-button" aria-label="Encoding">
		          <md-icon md-svg-icon="resources/img/icons/encoding.svg" style="color: white;"></md-icon>
		        </md-button>
		        <md-button class="md-icon-button" aria-label="Reports">
		          <md-icon md-svg-icon="resources/img/icons/reports.svg" style="color: white;"></md-icon>
		        </md-button>
		      </div>
		    </md-toolbar>
	    	
		    
	    </section> -->
	   
		<!-- <md-toolbar class="md-background">
		      <div class="md-toolbar-tools">
		        	<md-fab-speed-dial md-open=false md-direction="right"
	                       class="md-fling">
				        <md-fab-trigger>
				          <md-button aria-label="menu" class="md-fab md-primary">
				            <md-icon md-svg-src="resources/img/icons/menu.svg"></md-icon>
				          </md-button>
				        </md-fab-trigger>
				        <md-fab-actions>
				          <md-button aria-label="Twitter" class="md-fab md-raised md-mini">
				          	<md-tooltip md-direction="left">
					          Encoding
					        </md-tooltip>
				            <md-icon md-svg-src="resources/img/icons/twitter.svg" aria-label="Twitter"></md-icon>
				          </md-button>
				          <md-button aria-label="Facebook" class="md-fab md-raised md-mini">
				            <md-icon md-svg-src="resources/img/icons/facebook.svg" aria-label="Facebook"></md-icon>
				          </md-button>
				          <md-button aria-label="Google Hangout" class="md-fab md-raised md-mini">
				            <md-icon md-svg-src="resources/img/icons/hangout.svg" aria-label="Google Hangout"></md-icon>
				          </md-button>
				        </md-fab-actions>
			      </md-fab-speed-dial>
		      </div>
    	</md-toolbar> --> 
       <!-- <md-fab-toolbar md-open=false count="0"
                  md-direction="right">
	    <md-fab-trigger class="align-with-text">
	      <md-button aria-label="menu" class="md-fab md-primary">
	        <md-icon md-svg-src="resources/img/icons/menu.svg"></md-icon>
	      </md-button>
	    </md-fab-trigger>
	    <md-toolbar>
	      <md-fab-actions class="md-toolbar-tools">
	        <md-button aria-label="comment" class="md-icon-button md-warn">
	          <md-icon md-svg-src="img/icons/ic_comment_24px.svg"></md-icon>
	        </md-button>
	        <md-button aria-label="label" class="md-icon-button">
	          <md-icon md-svg-src="img/icons/ic_label_24px.svg"></md-icon>
	        </md-button>
	        <md-button aria-label="photo" class="md-icon-button">
	          <md-icon md-svg-src="img/icons/ic_photo_24px.svg"></md-icon>
	        </md-button>
	      </md-fab-actions>
	    </md-toolbar>
	  </md-fab-toolbar> -->
	  


	<script src="resources/vendors/jquery/jquery.min.js"></script>
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
	<script
		src="resources/vendors/angularjs/angular-message/messages.js"></script>
	
			
	<script
		src="resources/vendors/angularjs/angular-ui-router/release/angular-ui-router.min.js"></script>
	<script
		src="resources/vendors/angularjs/angular-md-data-table/md-data-table.min.js"></script>
		<script
		src="resources/vendors/angularjs/angularjs-highchart/adapters/standalone-framework.js"></script>
		<script
		src="resources/vendors/angularjs/angularjs-highchart/highcharts.js"></script>
		<script
		src="resources/vendors/angularjs/angularjs-highchart/modules/exporting.js"></script>
		<script
		src="resources/vendors/angularjs/angularjs-highchart/highcharts-more.js"></script>
		
	<script
		src="resources/vendors/angularjs/angularjs-highchart/highcharts-ng.min.js"></script>
	<script
		src="resources/vendors/angularjs/angular-storage/ngStorage.min.js"></script>
	<script
		src="resources/vendors/angularjs/angular-chart.js/Chart.bundle.min.js"></script>
		<script
		src="resources/vendors/angularjs/angular-chart.js/angular-chart.min.js"></script>
		
	<script src="resources/vendors/oclazyload/dist/ocLazyLoad.min.js"></script>
	<script src="resources/vendors/iconate/iconate.min.js"></script>
	<script src="<c:url value="resources/scripts/app.js"/>"></script>
	<script
		src="resources/scripts/directives/charts/chart.js"></script>
	
</body>

</html>
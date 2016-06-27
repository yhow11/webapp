'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('ispMintApp')
  .controller('HomeController', function($scope, $stateParams) {
	  $scope.org = $stateParams.org;
	  $scope.test = "wew";
	  $scope.selected = [];
	  $scope.table = [{
		  name: "John Fel",
		  age: "12",
		  number: 123,
		  email: "yhow11@gmail.com",
	  },{
		  name: "John Fel",
		  age: "12",
		  number: 123,
		  email: "yhow11@gmail.com",
	  },{
		  name: "John Fel",
		  age: "12",
		  number: 123,
		  email: "yhow11@gmail.com",
	  },{
		  name: "John Fel",
		  age: "12",
		  number: 123,
		  email: "yhow11@gmail.com",
	  },{
		  name: "John Fel",
		  age: "12",
		  number: 123,
		  email: "yhow11@gmail.com",
	  },{
		  name: "John Fel",
		  age: "12",
		  number: 123,
		  email: "yhow11@gmail.com",
	  },{
		  name: "John Fel",
		  age: "12",
		  number: 123,
		  email: "yhow11@gmail.com",
	  },{
		  name: "John Fel",
		  age: "12",
		  number: 123,
		  email: "yhow11@gmail.com",
	  },{
		  name: "John Fel",
		  age: "12",
		  number: 123,
		  email: "yhow11@gmail.com",
	  }];
	  
	  $scope.chartConfig = {

			  options: {
			      //This is the Main Highcharts chart config. Any Highchart options are valid here.
			      //will be overriden by values specified below.
			      chart: {
			          type: 'column'
			      },
			      tooltip: {
			          style: {
			              padding: 10,
			              fontWeight: 'bold'
			          }
			      }
			  },
			  //The below properties are watched separately for changes.

			  //Series object (optional) - a list of series using normal Highcharts series options.
			  series: [{
			     data: [10, 15, 12, 8, 7]
			  }],
			  //Title configuration (optional)
			  title: {
			     text: 'Monthly Top Worker'
			  },
			  //Boolean to control showing loading status on chart (optional)
			  //Could be a string if you want to show specific loading text.
			  loading: false,
			  //Configuration for the xAxis (optional). Currently only one x axis can be dynamically controlled.
			  //properties currentMin and currentMax provided 2-way binding to the chart's maximum and minimum
			  xAxis: {
			  currentMin: 0,
			  currentMax: 20,
			  title: {text: 'values'}
			  },
			  //Whether to use Highstocks instead of Highcharts (optional). Defaults to false.
			  useHighStocks: false,
			  //size (optional) if left out the chart will default to size of the div or something sensible.
			  size: {
			   height: 410
			  },
			  //function (optional)
			  func: function (chart) {
			   //setup some logic for the chart
			  }
			};
	  $scope.lineChartConfig = {

			  options: {
			      //This is the Main Highcharts chart config. Any Highchart options are valid here.
			      //will be overriden by values specified below.
			      chart: {
			          type: 'line'
			      },
			      tooltip: {
			          style: {
			              padding: 10,
			              fontWeight: 'bold'
			          }
			      }
			  },
			  //The below properties are watched separately for changes.

			  //Series object (optional) - a list of series using normal Highcharts series options.
			  series: [{
			     data: [10, 15, 12, 8, 7]
			  }],
			  //Title configuration (optional)
			  title: {
			     text: 'Monthly Top Worker'
			  },
			  //Boolean to control showing loading status on chart (optional)
			  //Could be a string if you want to show specific loading text.
			  loading: false,
			  //Configuration for the xAxis (optional). Currently only one x axis can be dynamically controlled.
			  //properties currentMin and currentMax provided 2-way binding to the chart's maximum and minimum
			  xAxis: {
			  currentMin: 0,
			  currentMax: 20,
			  title: {text: 'values'}
			  },
			  //Whether to use Highstocks instead of Highcharts (optional). Defaults to false.
			  useHighStocks: false,
			  //size (optional) if left out the chart will default to size of the div or something sensible.
			  size: {
			   height: 200
			  },
			  //function (optional)
			  func: function (chart) {
			   //setup some logic for the chart
			  }
			};
	  $scope.pieChart = {
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		            type: 'pie'
		        },
		        title: {
		            text: 'Browser market shares January, 2015 to May, 2015'
		        },
		        tooltip: {
		            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        series: [{
		            name: 'Brands',
		            colorByPoint: true,
		            data: [{
		                name: 'Microsoft Internet Explorer',
		                y: 56.33
		            }, {
		                name: 'Chrome',
		                y: 24.03,
		                sliced: true,
		                selected: true
		            }, {
		                name: 'Firefox',
		                y: 10.38
		            }, {
		                name: 'Safari',
		                y: 4.77
		            }, {
		                name: 'Opera',
		                y: 0.91
		            }, {
		                name: 'Proprietary or Undetectable',
		                y: 0.2
		            }]
		        }]
		    };
	  
  });

'use strict';
/**
 * @ngdoc function
 * @name fingerPrintApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the fingerPrintApp
 */
angular.module('fingerPrintApp')
  .controller('HomeController', function($scope, $stateParams, chartService) {
	  
	  $scope.columnData = {
		        chart: {
		            type: 'bar'
		        },
		        title: {
		            text: 'Monthly Candidates for Completion'
		        },
		        subtitle: {
		            text: '2016'
		        },
		        xAxis: {
		            categories: [
		                'Jan',
		                'Feb',
		                'Mar',
		                'Apr',
		                'May',
		                'Jun',
		                'Jul',
		                'Aug',
		                'Sep',
		                'Oct',
		                'Nov',
		                'Dec'
		            ],
		            crosshair: true
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: 'Rainfall (mm)'
		            }
		        },
		        tooltip: {
		            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
		            footerFormat: '</table>',
		            shared: true,
		            useHTML: true
		        },
		        plotOptions: {
		            column: {
		                pointPadding: 0.2,
		                borderWidth: 0
		            }
		        },
		        series: [{
		            name: 'Tokyo',
		            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

		        }, {
		            name: 'New York',
		            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]

		        }, {
		            name: 'London',
		            data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]

		        }, {
		            name: 'Berlin',
		            data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]

		        }]
		    };
	  
	  
	// Sample data for pie chart
      $scope.pieData = [{
              name: "Microsoft Internet Explorer",
              y: 56.33
          }, {
              name: "Chrome",
              y: 24.03,
              sliced: true,
              selected: true
          }, {
              name: "Firefox",
              y: 10.38
          }, {
              name: "Safari",
              y: 4.77
          }, {
              name: "Opera",
              y: 0.91
          }, {
              name: "Proprietary or Undetectable",
              y: 0.2
      }]
      
	  $scope.org = $stateParams.org;
	  $scope.test = "wew";
	  $scope.selected = [];
	  $scope.table = [{
		  name: "Jayson M. Cruz",
		  age: "23",
		  email: "jmc@gmail.com",
	  },{
		  name: "Nelson E. Reyes ",
		  age: "30",
		  email: "Nels@gmail.com",
	  },{
		  name: "Michelle C. Torre ",
		  age: "30",
		  email: "Mich11@gmail.com",
	  },{
		  name: "Richard M. Miranda ",
		  age: "30",
		  email: "chard21@gmail.com",
	  },{
		  name: "Daryl M. Monas ",
		  age: "30",
		  email: "darylmonas@gmail.com",
	  },{
		  name: "Vincent A. Santos ",
		  age: "31",
		  email: "vsantosjr@gmail.com",
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
			     text: 'Monthly Candidates for Completion'
			  },
			  //Boolean to control showing loading status on chart (optional)
			  //Could be a string if you want to show specific loading text.
			  loading: false,
			  //Configuration for the xAxis (optional). Currently only one x axis can be dynamically controlled.
			  //properties currentMin and currentMax provided 2-way binding to the chart's maximum and minimum
			  xAxis: {
			  categories: [
	                'Jan',
	                'Feb',
	                'Mar',
	                'Apr',
	                'May',
	                'Jun',
	                'Jul',
	                'Aug',
	                'Sep',
	                'Oct',
	                'Nov',
	                'Dec'
	            ],
	            crosshair: true,
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
	  $scope.monthyCandidatesOfCompletionCategory = "AREA";
	  chartService.getMonthyCandidatesOfCompletion($scope.monthyCandidatesOfCompletionCategory).then(function(data){
		  if(data.status == 200) {
				 if(data.data.status) {
					 $scope.chartConfig.series = data.data.data;
				}
			 }
			 
	  });
	  $scope.chartCategories = ["AREA","GROUP", "DISTRICT", "LOCAL"];
	  $scope.releoadMonthyCandidatesOfCompletion = function (category) {
		  chartService.getMonthyCandidatesOfCompletion(category).then(function(data){
			  if(data.status == 200) {
					 if(data.data.status) {
						 $scope.chartConfig.series = data.data.data;
					}
				 }
				 
		  }); 
	  }
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

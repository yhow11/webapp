'use strict';

angular.module('fingerPrintApp').factory(
		'metricSummaryService',
		function($http) {

			function handleSuccess(data) {
				return data;
			}

			function handleError(error) {
				return function() {
					return {
						success : false,
						message : error
					};
				};
			}
			return {
				getAll : function(type, offset, limit) {
					return $http.get(
							'metric/summary/getAll?type=' + type + '&offset='
									+ offset + '&limit=' + limit).then(
							handleSuccess,
							handleError('Error getting all users'));
				},
				getPage : function(value, page, limit) {
					if (value != 'ALL'){
						value = "%" + value + "%";
					} else {
						value = null;
					}
						
					return $http.post('metric/summary/getPage', {
						page : page,
						limit : limit,
						model : {
							metricType : value
						}
					}).then(handleSuccess,
							handleError('Error getting user by id'));
				},
				count : function() {
					return $http.get('metric/summary/count').then(
							handleSuccess,
							handleError('Error getting all users'));
				}
			}
		});

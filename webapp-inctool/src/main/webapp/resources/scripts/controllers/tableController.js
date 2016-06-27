'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:TableCtrl
 * @description # TableCtrl Controller of the sbAdminApp
 */
angular.module('sbAdminApp').controller(
		'TableCtrl',
		function($scope, $stateParams, DTOptionsBuilder, DTColumnBuilder) {
			this.dtOptions = DTOptionsBuilder.newOptions().withOption('ajax', {
				// Either you specify the AjaxDataProp here
				dataSrc : 'data',
				data: {
					appID: $stateParams.application.id
				},
				url : '/podio/api/app/post/datatables/entries',
				type : 'POST'
			}).withOption('processing', true).withOption('serverSide', true)
					.withPaginationType('full_numbers') .withOption('scrollY', '300px')
			        .withOption('scrollX', '100%')
			        .withOption('scrollCollapse', true);

			var fields = $stateParams.application.fields;
			this.dtColumns = [];
			for (var i = 0; i < fields.length - 1; i++) {
				this.dtColumns.push(DTColumnBuilder.newColumn(fields[i].configuration.label).withTitle(fields[i].configuration.label))
			}

		});
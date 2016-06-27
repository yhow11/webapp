'use strict';
/**
 * @ngdoc function
 * @name incToolApp.controller:TestController
 * @description # TestController Controller of the incToolApp
 */
angular.module('incToolApp').controller('TestController',
		function($scope, memberService) {
	
	
//	
			memberService.getAll().success(function(data){
				
			});
//			memberService.get('1').success(function(data){
//				
//			});
//			
//			memberService.remove('1').success(function(data){
//				
//			});
});
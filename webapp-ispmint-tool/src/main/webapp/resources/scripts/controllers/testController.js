'use strict';
/**
 * @ngdoc function
 * @name ispMintApp.controller:TestController
 * @description # TestController Controller of the ispMintApp
 */
angular.module('ispMintApp').controller('TestController',
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
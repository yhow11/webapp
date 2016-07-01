/*
 * Created By: John Fel Maulion
 * Created Date: 2016
 * Dependencies: app-sjqe.marketo.com/js/forms2/js/forms2.js
 * Description: marketform helper
 */
(function(window) {
	'use strict';
	function define_marketoFormCustomizer() {
		var MarketoFormCustomizer = {};
		MarketoFormCustomizer.readCookie = function(name) {
			var cookiename = name + "=";
			var ca = document.cookie.split(';');
			for (var i = 0; i < ca.length; i++) {
				var c = ca[i];
				while (c.charAt(0) == ' ')
					c = c.substring(1, c.length);
				if (c.indexOf(cookiename) == 0)
					return c.substring(cookiename.length, c.length);
			}
			return null;
		}

		MarketoFormCustomizer.setCookie = function(cname, cvalue, exdays) {
			var d = new Date();
			d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
			var expires = "expires=" + d.toUTCString();
			document.cookie = cname + "=" + cvalue + "; " + expires;
		}
		MarketoFormCustomizer.getUrlParams = function() {
			// This function is anonymous, is executed immediately and
			// the return value is assigned to QueryString!
			var query_string = {};
			var query = window.location.search.substring(1);
			var vars = query.split("&");
			for (var i = 0; i < vars.length; i++) {
				var pair = vars[i].split("=");
				// If first entry with this name
				if (typeof query_string[pair[0]] === "undefined") {
					query_string[pair[0]] = decodeURIComponent(pair[1]);
					// If second entry with this name
				} else if (typeof query_string[pair[0]] === "string") {
					var arr = [ query_string[pair[0]],
							decodeURIComponent(pair[1]) ];
					query_string[pair[0]] = arr;
					// If third or later entry with this name
				} else {
					query_string[pair[0]].push(decodeURIComponent(pair[1]));
				}
			}
			return query_string;
		};
		MarketoFormCustomizer.init = function() {
			var params = MarketoFormCustomizer.getUrlParams();
			if(!params[""]){
				var cookieStr = MarketoFormCustomizer.readCookie("marketoFormCookie");
				if (typeof cookieStr === "undefined"
	    			|| cookieStr == null
	    			|| cookieStr == "null") {
					var cookie = {};
					for(var key in params){
						if((key.indexOf("utm_source") > -1 || 
								key.indexOf("utm_source") > -1 ||
								key.indexOf("utm_medium") > -1 ||
								key.indexOf("utm_term") > -1 ||
								key.indexOf("utm_content") > -1 ||
								key.indexOf("utm_campaign") > -1 )){
							cookie[key] = params[key];
						}
					}
					MarketoFormCustomizer.setCookie("marketoFormCookie", JSON.stringify(cookie), 100);
				}
			}
			//Static Form
			var forms = document.getElementsByTagName("form");
			for(var index = 0; index < forms.length; index++){
				var inputs = forms[index].getElementsByTagName("input");
				var isMarketoForm = false;
				for(var indexInput = 0; indexInput < inputs.length; indexInput++){
					if(inputs[indexInput].name == "_mkt_trk"){
						isMarketoForm = true;
					}
				}
				if(isMarketoForm){
					var cookieStr = MarketoFormCustomizer.readCookie("marketoFormCookie");
					if (cookieStr != null) {
						cookie = JSON.parse(cookieStr);
						for(key in cookie){
							var inputFound = null;
			                for(var indexInput = 0; indexInput < inputs.length; indexInput++){
								if(inputs[indexInput].name == key){
									inputFound = inputs[indexInput];
								}
							}
			                if(inputFound != null){
			                	inputFound.value = cookie[key];
							} else {
								var input = document.createElement("input");
				                input.type = "hidden";
				                input.name = key;
				                input.value = cookie[key];
				                forms[index].appendChild(input);
							}
						}
					}
				}
				
			}
			//Async Form
			try{
				MktoForms2.whenReady(function(form) {
					// Put your code that uses the form object here
					var cookieStr = MarketoFormCustomizer.readCookie("marketoFormCookie");
					if (cookieStr != null) {
						cookie = JSON.parse(cookieStr);
						form.addHiddenFields(cookie);
						var formElems = form.getFormElem();
						for(var index = 0; index < formElems.length ; index++){
							for(var indexInput in formElems[index].getElementsByTagName("input")){
								var input = formElems[index].getElementsByTagName("input")[indexInput];
								if(input.type == "hidden" && (input.name.indexOf("utm_source") > -1 || 
							input.name.indexOf("utm_medium") > -1 ||
							input.name.indexOf("utm_term") > -1 ||
							input.name.indexOf("utm_content") > -1 ||
							input.name.indexOf("utm_campaign") > -1 )){
									input.value = cookie[input.name];
								}
							}
						}
						
					}
				});	
			}catch(e){
				console.log("Please load first the dependecy, app-sjqe.marketo.com/js/forms2/js/forms2.js");
			}
			
			

		};
		return MarketoFormCustomizer;
	}
	// define globally if it doesn't already exist
	if (typeof (MarketoFormCustomizer) === 'undefined') {
		window.MarketoFormCustomizer = define_marketoFormCustomizer();
		window.MarketoFormCustomizer.init();
	} else {
		console.log("MarketoFormCustomizer already defined.");
		window.MarketoFormCustomizer.init();
	}
	
	function isEmpty(obj) {
	    for(var prop in obj) {
	        if(obj.hasOwnProperty(prop))
	            return false;
	    }

	    return true && JSON.stringify(obj) === JSON.stringify({});
	}
})(window);
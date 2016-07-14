/*
 * Created By: John Fel Maulion
 * Created Date: 2016
 * Dependencies: sock.js and stomp.js
 * Description: JS Library that can tracks user events such as click, hover, and visit.
 */
(function(window){
    'use strict';
    function define_fingerPrint(){
        var FingerPrint = {};
        FingerPrint.readCookie = function (name) {
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

        FingerPrint.setCookie = function(cname, cvalue, exdays) {
    		var d = new Date();
    		d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    		var expires = "expires=" + d.toUTCString();
    		document.cookie = cname + "=" + cvalue + "; " + expires;
    	}
        FingerPrint.generateTimeStamp = function(){
    		
    		var fingerPrintData = typeof window.localStorage.fingerPrintData !== "undefined"? JSON.parse(window.localStorage.fingerPrintData):{};
    		fingerPrintData.timeStamp = new Date().getTime();
    		FingerPrint.setCookie('fingerprint_timestamp', fingerPrintData.timeStamp, 10);
    		window.localStorage.fingerPrintData = JSON.stringify(fingerPrintData);
        }
        FingerPrint.generateFingerPrints = function(){

    		var client = new ClientJS(); 
    		
    		//Device Data Points
    		var screenPrint = client.getScreenPrint();
    		var OS = client.getOS();
    		var CPU = client.getCPU();
    		var device = client.getDevice();
    		var timeZone = client.getTimeZone();
    		var language = client.getLanguage();
    		var fonts = client.getFonts();
    		
    		var deviceDataPoints = screenPrint + OS + CPU + device + timeZone + language + fonts;
    		
    		FingerPrint.setCookie('device', device, 10);
    		FingerPrint.setCookie('cpu', CPU, 10);
    		FingerPrint.setCookie('screenPrint', screenPrint, 10);
    		FingerPrint.setCookie('os', OS, 10);
    		FingerPrint.setCookie('timeZone', timeZone, 10);
    		FingerPrint.setCookie('language', language, 10);
    		FingerPrint.setCookie('fonts', fonts, 10);
    		
    		
    		//Browser Data Points

    		var user_agent = client.getUserAgent();
    		var browser_browser = client.getBrowser();
    		var browser_browser_version = client.getBrowserVersion();
    		var browser_engine = client.getEngine();
    		var browser_engine_version = client.getEngineVersion();
    		var browser_localstorage = client.isLocalStorage();
    		var browser_session_storage = client.isSessionStorage();
    		var browser_plugins = client.getPlugins();
    		var browser_canvas_print = BeaverBird.data().canvasFingerprint;
    		var browser_mime_type = client.getMimeTypes();
    		var browser_is_mime_type = client.isMimeTypes();
    		var browser_system_language = client.getSystemLanguage();

    		var browserDataPoints = browser_browser + browser_browser_version + browser_engine + browser_engine_version + browser_localstorage 
    		+ browser_session_storage + browser_plugins + browser_canvas_print + browser_mime_type
    		+ browser_is_mime_type + browser_system_language + user_agent;
    		
    		FingerPrint.setCookie('user_agent', user_agent, 10);
    		FingerPrint.setCookie('browser_browser', browser_browser, 10);
    		FingerPrint.setCookie('browser_browser_version', browser_browser_version, 10);
    		FingerPrint.setCookie('browser_engine', browser_engine, 10);
    		FingerPrint.setCookie('browser_engine_version', browser_engine_version, 10);
    		FingerPrint.setCookie('browser_localstorage', browser_localstorage, 10);
    		FingerPrint.setCookie('browser_session_storage', browser_session_storage, 10);
    		FingerPrint.setCookie('browser_plugins', browser_plugins, 10);
    		FingerPrint.setCookie('browser_canvas_print', browser_canvas_print, 10);
    		FingerPrint.setCookie('browser_mime_type', browser_mime_type, 10);
    		FingerPrint.setCookie('browser_is_mime_type', browser_is_mime_type, 10);
    		FingerPrint.setCookie('browser_system_language', browser_system_language, 10);

    		var deviceFingerPrint = CryptoJS.MD5(deviceDataPoints).toString(CryptoJS.enc.Base64);
    		var browserFingerPrint = CryptoJS.MD5(browserDataPoints).toString(CryptoJS.enc.Base64);

    		var fingerPrintData = typeof window.localStorage.fingerPrintData !== "undefined"? JSON.parse(window.localStorage.fingerPrintData):{};
    		fingerPrintData.browserFP = browserFingerPrint;
    		fingerPrintData.deviceFP = deviceFingerPrint;
    		
    		window.localStorage.fingerPrintData = JSON.stringify(fingerPrintData);
    		FingerPrint.generateTimeStamp();
        }
        FingerPrint.generateSessionID = function(){
        	var d = new Date().getTime();
        	if (window.performance && typeof window.performance.now === "function") {
        		d += performance.now(); //use high-precision timer if available
        	}
        	var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
        			function(c) {
        				var r = (d + Math.random() * 16) % 16 | 0;
        				d = Math.floor(d / 16);
        				return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
        			});
        	FingerPrint.setCookie('guid', uuid, 1000);
        	var fingerPrintData = typeof window.localStorage.fingerPrintData !== "undefined"? JSON.parse(window.localStorage.fingerPrintData):{};
    		fingerPrintData.sessionID = uuid;
    		
    		window.localStorage.fingerPrintData = JSON.stringify(fingerPrintData);

        }
        FingerPrint.getData = function(){
        	return JSON.parse(window.localStorage.fingerPrintData);
        }
        FingerPrint.init = function(){

        	var timestamp_cookie = FingerPrint.readCookie('fingerprint_timestamp');

        	if (timestamp_cookie != null) {
        		FingerPrint.generateTimeStamp();
        	} else if (timestamp_cookie == null) {
        		FingerPrint.generateFingerPrints();
        	}

        	var guid = FingerPrint.readCookie('guid');
        	if (typeof guid === "undefined"
        			|| guid == null
        			|| guid == "null") {
        		FingerPrint.generateSessionID();
        	} 
        	var getUrl = window.location;
        	var baseUrl = getUrl.protocol + "//" + getUrl.host + "/"
    		+ getUrl.pathname.split('/')[1];
        	var socket = new SockJS(baseUrl + '/send');
			var client = Stomp.over(socket);
			client.connect({}, function(frame) {
				FingerPrint.client = client;
				window.onmouseover = function(e) {
					if (e.target.localName == 'a') {
						var href = e.target.href;
						var title = e.target.title;
						FingerPrint.send("hover", title, href);
					}
					console.log("user hover.");
				}
				window.onclick = function(e) {
					var href = "N/A";
					var title = "N/A";
					if (e.target.localName == 'a') {
						href = e.target.href;
						title = e.target.title;
					} else if (e.target.localName == 'button') {
						title = e.target.label;
					} else if (e.target.localName == 'img') {
						var parent = e.target.parentElement;
						if(parent.localName == 'a') {
							href = parent.href;
							title = parent.title;
						}
					}
					FingerPrint.send("clicked", title, href);
					console.log("user clicked.");
				}
				window.onload = function () {
					FingerPrint.send("visited", document.title, window.location.href);
					console.log("user visited.");
				}
				window.onbeforeunload = function () {
					FingerPrint.send("leaved", document.title, window.location.href);
				}
	        	console.log("init completed.");
			});
        };
        FingerPrint.send = function(type, title, url){
        	if(FingerPrint.client){
        		var data = FingerPrint.getData();
        		FingerPrint.client
        		.send(
        				"/app/send",
        				{},
        				JSON
        						.stringify({
        							log : data.lead_id
        									+ "|"
        									+ data.browserFP
        									+ "|"
        									+ data.deviceFP
        									+ "|"
        									+ data.timeStamp
        									+ "|"+type+"|"
        									+ url
        									+ "|"
        									+ title
        									+ "|"
        									+ data.sessionID
        						}));
        	} else {
        		console.log("client is not yet connected.");
        	}
        	
        }
        FingerPrint.show = function(){
        	console.log(FingerPrint.getData());
        }
        return FingerPrint;
    }
    //define globally if it doesn't already exist
    if(typeof(FingerPrint) === 'undefined'){
        window.FingerPrint = define_fingerPrint();
        window.FingerPrint.init();
    }
    else{
        console.log("Fingerprint already defined.");
        window.FingerPrint.init();
    }
})(window);
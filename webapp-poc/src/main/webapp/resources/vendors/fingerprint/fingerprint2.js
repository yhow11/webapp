/*
 * Created By: John Fel Maulion
 * Created Date: 2016
 * Dependencies: sock.js and stomp.js
 * Description: JS Library that can tracks user events such as click, hover, and visit.
 */
(function(window){
    'use strict';
    function define_cookieutil(){
    	var COOKIEUTIL  = {};
    	COOKIEUTIL.read = function(name){
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
    	};
    	COOKIEUTIL.write = function(cname, cvalue) {
    		var exdays = 40;
    		var d = new Date();
    		d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    		var expires = "expires=" + d.toUTCString();
    		document.cookie = cname + "=" + cvalue + "; " + expires;
    	};
    	return COOKIEUTIL;
    }
    function define_clientutil(){
    	
    	var CLIENTUTIL  = {};
    	CLIENTUTIL.baseURL
    	CLIENTUTIL.init = function(baseURL){
    		CLIENTUTIL.baseURL = baseURL;
    	};
    	CLIENTUTIL.send = function(visitorID, fingerprintdata, type, title, url){
    		var data = fingerprintdata;
    		var xhttp = new XMLHttpRequest();
    		xhttp.open("POST", CLIENTUTIL.baseURL+'logs/send', false);
    		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        	xhttp.onreadystatechange = function() {
    			  if (this.readyState == 4 && this.status == 200) {
    				  
    			  }
    			};
			
			xhttp.send("log="+data.lead_id
					+ "||"
					+ data.browserFP
					+ "||"
					+ data.deviceFP
					+ "||"
					+ data.timestamp
					+ "||"+type+"||"
					+ url
					+ "||"
					+ title
					+ "||"
					+ data.sessionID
					+ "||"
					+ (data.elapsedtime || 0)
					+ "||"
					+ (data.country || "unknown")
					+ "||"
					+ visitorID);
        	
        }
    	return CLIENTUTIL;
    }
    function define_elementutil(){
    	var ELEMENTUTIL  = {};
    	ELEMENTUTIL.findParentByTagName = function (element, tagName) {
            var parent = element;

            while (parent !== null && parent.tagName !== tagName.toUpperCase()) {
                parent = parent.parentNode;
            }

            return parent;
        };
    	return ELEMENTUTIL;
    }
    function define_elementutil(){
    	var ELEMENTUTIL  = {};
    	ELEMENTUTIL.findParentByTagName = function (element, tagName) {
            var parent = element;

            while (parent !== null && parent.tagName !== tagName.toUpperCase()) {
                parent = parent.parentNode;
            }

            return parent;
        };
    	return ELEMENTUTIL;
    }
    function define_uuid(){
    	var UUID  = {};
    	UUID.generate = function () {
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
            return uuid;
        };
    	return UUID;
    }
    function define_timerutil(COOKIEUTIL){
    	var TIMERUTIL  = {};
    	
    	TIMERUTIL.getTime = function (baseURL) {
    		 return new Promise(
    			        function(resolve, reject) {
    			        	var xhttp = new XMLHttpRequest();
    			        	xhttp.onreadystatechange = function() {
    			    			  if (this.readyState == 4 && this.status == 200) {
    			    				  resolve(JSON.parse(xhttp.responseText).data[0]);
    			    			  }
    			    			};
    						xhttp.open("GET", baseURL+'currentTime', true);
    						xhttp.send();
    			        }
    			    );
        };
        TIMERUTIL.init = function(baseURL){
        	
    		setInterval(function(){ 
    			if(TIMERUTIL.currentime){
    				var starttime = TIMERUTIL.currentime;
        			var date = new Date(Number(starttime));
        			date.setSeconds(date.getSeconds() + 1);
        			TIMERUTIL.currentime = date.getTime();
    			}
    			
			}, 1000);
    	};
    	TIMERUTIL.getElapsedTime = function(endtime){
    		var starttime = COOKIEUTIL.read("starttime");
    		return endtime - starttime;
    	};
    	window.TIMERUTIL = TIMERUTIL;
    	return TIMERUTIL;
    }
    function define_fingerPrint(COOKIEUTIL, CLIENTUTIL, ELEMENTUTIL, UUID, TIMERUTIL){
        var FingerPrint = {};
        FingerPrint.getDeviceFP = function(client){
    		var screenPrint = client.getScreenPrint();
    		var OS = client.getOS();
    		var CPU = client.getCPU();
    		var device = client.getDevice();
    		var timeZone = client.getTimeZone();
    		var language = client.getLanguage();
    		var fonts = client.getFonts();
    		
    		var deviceDataPoints = screenPrint + OS + CPU + device + timeZone + language + fonts;
    		
    		return CryptoJS.MD5(deviceDataPoints).toString(CryptoJS.enc.Base64);
        };
        FingerPrint.getBrowserFP = function(client){
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
    		
    		return CryptoJS.MD5(browserDataPoints).toString(CryptoJS.enc.Base64);
        };
        FingerPrint.getSessionID = function(){
        	var sessionID = COOKIEUTIL.read('sessionID');
        	if (typeof sessionID === "undefined"
        			|| sessionID == null
        			|| sessionID == "null") {
        		sessionID = UUID.generate();
            	COOKIEUTIL.write('sessionID', sessionID);
            	return sessionID;
        	} 
        	return sessionID;
        };
        FingerPrint.getData = function(){
        	var data = typeof window.localStorage.fingerPrintData !== "undefined"? JSON.parse(window.localStorage.fingerPrintData):{};
        	data.timestamp = TIMERUTIL.currentime;
        	return data;
        };
        FingerPrint.setData = function(data){
        	window.localStorage.fingerPrintData = JSON.stringify(data);
        };
        FingerPrint.generate = function(){

    		var client = new ClientJS(); 
    		
    		var deviceFingerPrint = FingerPrint.getDeviceFP(client);
    		var browserFingerPrint = FingerPrint.getBrowserFP(client);

    		var fingerPrintData = FingerPrint.getData();
    		fingerPrintData.browserFP = browserFingerPrint;
    		fingerPrintData.deviceFP = deviceFingerPrint;
    		fingerPrintData.sessionID = FingerPrint.getSessionID();
    		FingerPrint.setData(fingerPrintData);
    		
        };
        FingerPrint.sendClickedEvent = function(e){
        	var visitorID = COOKIEUTIL.read("visitorID");
        	if(visitorID){
	        	var href = e.target.href;
				var title = e.target.title != ''?  e.target.title:e.target.innerText;;
				CLIENTUTIL.send(visitorID, FingerPrint.getData(), "hover", title, href);
        	}
        };
        FingerPrint.sendHoverEvent = function(e){
        	var visitorID = COOKIEUTIL.read("visitorID");
        	if(visitorID){
	        	var href = e.target.href;
				var title = e.target.title != ''?  e.target.title:e.target.innerText;;
				CLIENTUTIL.send(visitorID, FingerPrint.getData(), "hover", title, href);
        	}
        };
        FingerPrint.sendLeavedEvent = function(){
        	var visitorID = COOKIEUTIL.read("visitorID");
        	if(visitorID){
        		var data = FingerPrint.getData();
    			data.elapsedtime = TIMERUTIL.getElapsedTime(data.timestamp);
    			CLIENTUTIL.send(visitorID, data,"leaved", document.title, window.location.href);
        	}
        };
        FingerPrint.sendVisitedEvent = function(){
        	var visitorID = COOKIEUTIL.read("visitorID");
        	if(typeof visitorID !== 'undefined'){
        		TIMERUTIL.getTime(baseURL).then(function(data){
        			TIMERUTIL.currentime = data.trim();
        			COOKIEUTIL.write("starttime", data);
        			CLIENTUTIL.send(visitorID, FingerPrint.getData(),"visited", document.title, window.location.href);
        		});
        		
            	var data = FingerPrint.getData();
    			var xhttp = new XMLHttpRequest();
            	xhttp.onreadystatechange = function() {
        			  if (this.readyState == 4 && this.status == 200) {
        				  console.log(xhttp.responseText);
        			  }
        			};
    			xhttp.open("GET", baseURL+'segment/getByVisitor?visitorID='+visitorID, true);
    			xhttp.send();
        	}

			
        	
        };
        FingerPrint.getVisitor = function (baseURL, data) {
   		 return new Promise(
   			        function(resolve, reject) {
   			        	var visitorID = COOKIEUTIL.read("visitorID");
   			        	if(visitorID){
   			        		resolve(visitorID);
   			        	} else {
   	   			        	var xhttp = new XMLHttpRequest();
   	   			        	xhttp.onreadystatechange = function() {
   	   			    			  if (this.readyState == 4 && this.status == 200) {
   	   			    				  var ID = JSON.parse(xhttp.responseText).id;
   	   			    				  COOKIEUTIL.write('visitorID', ID);
   	   			    				  resolve(ID);
   	   			    			  }
   	   			    			};
   	   						xhttp.open("GET", baseURL+'visitor/get?sessionID='+data.sessionID+"&browserFP="+data.browserFP+"&deviceFP="+data.deviceFP, true);
   	   						xhttp.send();
   			        	}
   			        
   			        }
   			    );
       };
        FingerPrint.init = function(baseURL){
        	
        	FingerPrint.generate();
        	
        	$.get("http://ipinfo.io", function (response) {
        		var fingerPrintData = FingerPrint.getData();
        		fingerPrintData.country = response.country
        		FingerPrint.setData(fingerPrintData);
        	}, "jsonp");
        	
        	
        	TIMERUTIL.init(baseURL);
        	CLIENTUTIL.init(baseURL);
        	
        	FingerPrint.getVisitor(baseURL, FingerPrint.getData()).then(function(data){
        		FingerPrint.sendVisitedEvent();
        	});
			window.addEventListener("mouseover", function (e) {
				if (e.target.localName == 'a') {
					FingerPrint.sendHoverEvent(e);
				}
			}, false); 
			
			document.documentElement.addEventListener("click", function handleAnchorClick(event) {
				FingerPrint.sendClickedEvent(event);
			}, false);
			
			window.addEventListener("hashchange", function (e) {
				FingerPrint.sendLeavedEvent();
				FingerPrint.sendVisitedEvent();
				
			}, false); 
			window.addEventListener("beforeunload", function (e) {
				FingerPrint.sendLeavedEvent();
			}, false);

			
        	console.log("init completed.");
        };
        

        FingerPrint.show = function(){
        	console.log(FingerPrint.getData());
        }
        return FingerPrint;
    }
    //define globally if it doesn't already exist
    if(typeof(FingerPrint) === 'undefined'){
        window.FingerPrint = define_fingerPrint(define_cookieutil(), define_clientutil(), define_elementutil(), define_uuid(), define_timerutil(define_cookieutil()));
    }
    else{
        console.log("Fingerprint already defined.");
    }
   
})(window);
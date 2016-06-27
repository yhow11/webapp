
function test () {	
   var baseURL = "http://188.166.183.50/marketo_fingerprint";
   
   document.getElementById("browser_finger_print").innerHTML =   window.localStorage.browser_finger_print ;
   document.getElementById("device_finger_print").innerHTML =  window.localStorage.device_finger_print;
   document.getElementById("time_stamp").innerHTML = window.localStorage.time_stamp ;
   document.getElementById("lead_id").innerHTML =  window.localStorage.lead_id ;
		   
		 
	 
	 
	  //send_data_to_server();
   var timestamp_cookie = readCookie('fingerprint_timestamp');
   if ( timestamp_cookie != null) {
	  
	       send_cookie_lead_id ();

	 } else if (timestamp_cookie == null && ( window.localStorage.lead_id == undefined || window.localStorage.lead_id == 'undefined')) {
	 
	       send_data_to_server ();
		   
	 } else if (timestamp_cookie == null && ( window.localStorage.lead_id != undefined || window.localStorage.lead_id != 'undefined')) {
	 
	    send_cookie_lead_id ();
	 
	 }

   window.localStorage.guid = readCookie('guid');
   if(typeof window.localStorage.guid === "undefined" || window.localStorage.guid == null|| window.localStorage.guid == "null"){
	   window.localStorage.guid = generateUUID();
	   setCookie('guid', window.localStorage.guid, 1000); ;
	   document.getElementById("guid").innerHTML =  window.localStorage.guid ;
   } else {
	   document.getElementById("guid").innerHTML =  window.localStorage.guid ; 
   }
   function send_cookie_lead_id() {
	  
	    var munchkin_cookie = readCookie('_mkto_trk');
	    var munchkin_cookie_array = munchkin_cookie.split("&");
	    var munchkin_cookie = "cookie="+munchkin_cookie_array[0]+"&token="+munchkin_cookie_array[1];
		var params = munchkin_cookie+"&lead_id="+window.localStorage.lead_id
		
		var munchkin_cookie = readCookie('_mkto_trk');
	    var http = new XMLHttpRequest();
      var url = baseURL+"/send_cookie_lead_id.php";
		
		http.open("POST", url, true);

//Send the proper header information along with the request
   http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");


   http.onreadystatechange = function() {
      if(http.readyState == 4 && http.status == 200) {
		  
		    var parsed_data = JSON.parse(http.responseText);
			if (parsed_data.result == 0  || parsed_data.result == '0'){
			
			 window.localStorage.time_stamp =  parsed_data.time_stamp;
		     setCookie('fingerprint_timestamp', parsed_data.time_stamp, 10);
			    
			} else {
			
			       delete window.localStorage.lead_id;
			}
		   
    }
  }

	http.send(params);
	 
	 }	 

  function send_data_to_server () {
	
	var client = new ClientJS(); // Create A New Client Object
	var screenPrint = client.getScreenPrint(); // Get Screen Print
	setCookie('screenPrint', screenPrint, 10);
  var OS = client.getOS(); // Get OS Version
	setCookie('os', OS, 10);
	var CPU = client.getCPU(); // Get CPU Architecture
	setCookie('cpu', CPU, 10);
	var device = client.getDevice(); // Get Device
	setCookie('device', device, 10);
	var timeZone = client.getTimeZone(); // Get Time Zone
	setCookie('timeZone', timeZone, 10);
	var language = client.getLanguage(); // Get User Language
	setCookie('language', language, 10);
  var fonts = client.getFonts();
	setCookie('fonts', fonts, 10);
	
	var user_agent =  client.getUserAgent();
	var browser_browser = client.getBrowser();
	var browser_browser_version = client.getBrowserVersion();
	var browser_engine = client.getEngine();
  var browser_engine_version = client.getEngineVersion();
	var browser_localstorage =  client.isLocalStorage();
	var browser_session_storage =  client.isSessionStorage();
	var browser_plugins =  client.getPlugins();
	var browser_canvas_print = BeaverBird.data().canvasFingerprint;
	var browser_mime_type = client.getMimeTypes();
  var browser_is_mime_type = client.isMimeTypes();
	var browser_system_language = client.getSystemLanguage(); 

   setCookie('user_agent', user_agent, 10);
   setCookie('browser_browser', browser_browser, 10);
	 setCookie('browser_browser_version', browser_browser_version, 10);
	 setCookie('browser_engine', browser_engine, 10);
	 setCookie('browser_engine_version', browser_engine_version, 10);
	 setCookie('browser_localstorage', browser_localstorage, 10);
	 setCookie('browser_session_storage', browser_session_storage, 10);
	 setCookie('browser_plugins', browser_plugins, 10);
	 setCookie('browser_canvas_print', browser_canvas_print, 10);
	 setCookie('browser_mime_type', browser_mime_type, 10);
	 setCookie('browser_is_mime_type', browser_is_mime_type, 10);
	 setCookie('browser_system_language', browser_system_language, 10);
	  
	

	var munchkin_cookie = readCookie('_mkto_trk');
	console.log("cookie"+munchkin_cookie);
	var http = new XMLHttpRequest();
  var url = baseURL+"/hoosh_server.php";
  var params = "available_resolution="+client.getAvailableResolution()+"&color_depth="+client.getColorDepth()+"&timeZone="+timeZone+"&os="+OS+"&";
  var browser_params1 = "browser_user_agent="+user_agent+"&browser="+browser_browser+"&browser_version="+browser_browser_version+"&";
	var browser_params2 = "browser_engine="+browser_engine+"&browser_engine_version="+browser_engine_version+"&browser_localstorage="+browser_localstorage+"&";
	var browser_params3 = "browser_session_storage="+browser_session_storage+"&browser_plugins="+browser_plugins+"&browser_canvas_print="+browser_canvas_print+"&";
	var browser_params4 = "browser_mime_type="+browser_mime_type+"&browser_is_mime_type="+browser_is_mime_type+"&browser_system_language="+browser_system_language+"&";
  var munchkin_cookie_array = munchkin_cookie.split("&");
	var munchkin_cookie = "cookie="+munchkin_cookie_array[0]+"&token="+munchkin_cookie_array[1];
	

	 params = params+browser_params1+browser_params2+browser_params3+browser_params4+munchkin_cookie;
	 
	 var passhash = CryptoJS.MD5(params);
	 console.log(passhash.toString(CryptoJS.enc.Base64));
	
	http.open("POST", url, true);

//Send the proper header information along with the request
   http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   http.setRequestHeader("Content-length", params.length);
   http.setRequestHeader("Connection", "close");

   http.onreadystatechange = function() {
      if(http.readyState == 4 && http.status == 200) {
		  
		    var parsed_data = JSON.parse(http.responseText);
			
		     window.localStorage.time_stamp =  parsed_data.time_stamp;
		     window.localStorage.browser_finger_print = parsed_data.browser_finger_print
		     window.localStorage.device_finger_print = parsed_data.device_finger_print;
		     window.localStorage.lead_id = parsed_data.lead_id;
		     setCookie('fingerprint_timestamp', parsed_data.time_stamp, 10);
		   
		   document.getElementById("browser_finger_print").innerHTML =  parsed_data.browser_finger_print;
		   document.getElementById("device_finger_print").innerHTML = parsed_data.device_finger_print;
		   document.getElementById("time_stamp").innerHTML = parsed_data.time_stamp;
		   document.getElementById("lead_id").innerHTML = parsed_data.lead_id;
		   
		     console.log("browser_finger_print "+parsed_data.browser_finger_print);
		   console.log("device_finger_print "+parsed_data.device_finger_print);
		   console.log("time_stamp "+parsed_data.time_stamp);
		   console.log("lead_id "+parsed_data.lead_id);
		   
		 
	         setCookie('lead_id', parsed_data.lead_id, 10);
 
    }
  }

	http.send(params);
	
	}
	
	
	
	function readCookie(name) {
		var cookiename = name + "=";
		var ca = document.cookie.split(';');
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(cookiename) == 0) return c.substring(cookiename.length,c.length);
		}
		return null;
	}
	
	
	function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + "; " + expires;
}


	
}

function delete_cookie( name ) {
document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

function clearing_all_data() {
	
	  window.localStorage.clear();
	  document.getElementsByClassName('show_data').innerHTML = "";
	  delete_cookie('fingerprint_timestamp');
	  delete_cookie('browser_finger_print');
	  delete_cookie('device_finger_print');
	  delete_cookie('lead_id');
	 
	}

function generateUUID(){
    var d = new Date().getTime();
    if(window.performance && typeof window.performance.now === "function"){
        d += performance.now(); //use high-precision timer if available
    }
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    return uuid;
}
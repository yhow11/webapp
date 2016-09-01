function loadScript(url, callback) {

	var script = document.createElement("script")
	script.type = "text/javascript";

	if (script.readyState) { // IE
		script.onreadystatechange = function() {
			if (script.readyState == "loaded"
					|| script.readyState == "complete") {
				script.onreadystatechange = null;
				callback();
			}
		};
	} else { // Others
		script.onload = function() {
			callback();
		};
	}

	script.src = url;
	document.getElementsByTagName("head")[0].appendChild(script);
}
loadScript(
		"http://54.187.110.176:8191/webapp-poc/resources/vendors/fingerprint/fingerprint2.js",
		function() {
			FingerPrint.init();
			console.log("fingerprint tracker has been loaded");
		});
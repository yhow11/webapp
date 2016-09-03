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

var baseURL = 'http://54.187.110.176:8191/webapp-poc/';
var fingerprintDir = baseURL+'resources/vendors/fingerprint/';
var cryptoDir = baseURL+'resources/vendors/crypto/';
var websocketDir = baseURL+'resources/vendors/websocket/';
loadScript(
		fingerprintDir+"client.min.js",
		function() {
			loadScript(
					fingerprintDir+"beaverbird.min.js",
					function() {
						loadScript(
								cryptoDir+'md5.js',
								function() {
									loadScript(
											websocketDir+"sockjs.js",
											function() {
												loadScript(
														websocketDir+"stomp.js",
														function() {
															loadScript(
																	fingerprintDir+"fingerprint2.js",
																	function() {
																		FingerPrint.init(baseURL);
																		console.log("fingerprint tracker has been loaded");
																	});
														});
											});
								});
					});
		});

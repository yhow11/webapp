package common;

import java.net.MalformedURLException;
import java.net.URL;

public class URLUtil {

	public static String getRealURL(String url) throws MalformedURLException {
		String result = "";
		try{
			URL urlObject = new URL(url);
			result = urlObject.getProtocol()+"://"+urlObject.getHost()+urlObject.getPath();
		} catch(Exception e ){
			result = "";
		}
		return result;
	}
}

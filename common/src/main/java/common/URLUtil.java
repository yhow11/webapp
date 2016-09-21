package common;

import java.net.MalformedURLException;
import java.net.URL;

public class URLUtil {

	public static String getRealURL(String url) throws MalformedURLException {
		URL urlObject = new URL(url);
		return urlObject.getProtocol()+"://"+urlObject.getHost()+urlObject.getPath();
	}
}

/*******************************************************************************
 * Author: vandopha                                     		  
 * Date: Mar 22, 2021                                             
 * Description: Using The URL Class
/******************************************************************************/
package ch02_Netword_Addressing;

import java.io.IOException;
import java.net.URL;

public class p04_UsingTheURLClass {

	public static void main(String[] args) {

		usingTheURLClass();

	}

	/**
	 * URL class
	 */
	private static void usingTheURLClass() {

		try {
			URL url;
			url = new URL("https://packtpub.com/books/content/support");
//			url = new URL("https://www.packtpub.com");
			// The following Generates: java.net.MalformedURLException: no protocol:
			// www.packtpub.com
//			url = new URL("www.packtpub.com");
//			url = new URL("http://pluto.jhuapl.edu/");
//			url = new URL("http", "pluto.jhuapl.edu", 80, "News-Center/index.php");
//			url = new URL("https://en.wikipedia.org/wiki/Uniform_resource_locator#Syntax");
//			url = new URL("https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=url+syntax");
			System.out.println();
			displayURL(url);
			System.out.println("getContent: " + url.getContent());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * display URL info
	 * 
	 * @param url
	 */
	private static void displayURL(URL url) {

		System.out.println("URL: " + url);
		System.out.printf("  Protocol: %-32s  Host: %-32s\n", url.getProtocol(), url.getHost());
		System.out.printf("      Port: %-32d  Path: %-32s\n", url.getPort(), url.getPath());
		System.out.printf(" Reference: %-32s  File: %-32s\n", url.getRef(), url.getFile());
		System.out.printf(" Authority: %-32s Query: %-32s\n", url.getAuthority(), url.getQuery());
		System.out.println("User Info: " + url.getUserInfo());

	}

}

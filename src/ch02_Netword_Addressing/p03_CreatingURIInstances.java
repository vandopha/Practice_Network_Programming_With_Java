/*******************************************************************************
 * Author: vandopha                                     		  
 * Date: Mar 22, 2021                                             
 * Description: Creating URI instances
/******************************************************************************/
package ch02_Netword_Addressing;

import java.net.URI;
import java.net.URISyntaxException;

public class p03_CreatingURIInstances {

	public static void main(String[] args) {

		creatingURIInstances();

	}

	/**
	 * URI instance
	 */
	private static void creatingURIInstances() {

		try {
			// Creating URI instances
			URI uri = new URI("https://www.packtpub.com/books/content/support");
			uri = new URI("https://en.wikipedia.org/wiki/" + "URL_normalization#Normalization_process");
			uri = new URI("https", "en.wikipedia.org", "/wiki/URL_normalization", "Normalization_process");
			displayURI(uri);
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * display URI info
	 * 
	 * @param uri
	 */
	private static void displayURI(URI uri) {

		System.out.println("URI Information");
		System.out.println("getAuthority			: " + uri.getAuthority());
		System.out.println("getScheme				: " + uri.getScheme());
		System.out.println("getSchemeSpecificPart	: " + uri.getSchemeSpecificPart());
		System.out.println("getHost					: " + uri.getHost());
		System.out.println("getPath					: " + uri.getPath());
		System.out.println("getQuery				: " + uri.getQuery());
		System.out.println("getFragment				: " + uri.getFragment());
		System.out.println("getUserInfo				: " + uri.getUserInfo());
		System.out.println("normalize				: " + uri.normalize());

	}

}

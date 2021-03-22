/******************************************************************************
 * Examples
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch02_Netword_Addressing;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

public class Examples {

	public static void main(String... args) {

		usingTheNetworkInterfaceClass();

		gettingAMACAddress();

		creatingURIInstances();

		UsingTheURLClass();

	}

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

	private static void displayURI(URI uri) {

		System.out.println("URI Information");

		System.out.println("getAuthority: " + uri.getAuthority());

		System.out.println("getScheme: " + uri.getScheme());

		System.out.println("getSchemeSpecificPart: " + uri.getSchemeSpecificPart());

		System.out.println("getHost: " + uri.getHost());

		System.out.println("getPath: " + uri.getPath());

		System.out.println("getQuery: " + uri.getQuery());

		System.out.println("getFragment: " + uri.getFragment());

		System.out.println("getUserInfo: " + uri.getUserInfo());

		System.out.println("normalize: " + uri.normalize());

	}

	private static void displayURL(URL url) {

		System.out.println("URL: " + url);

		System.out.printf("  Protocol: %-32s  Host: %-32s\n", url.getProtocol(), url.getHost());

		System.out.printf("      Port: %-32d  Path: %-32s\n", url.getPort(), url.getPath());

		System.out.printf(" Reference: %-32s  File: %-32s\n", url.getRef(), url.getFile());

		System.out.printf(" Authority: %-32s Query: %-32s\n", url.getAuthority(), url.getQuery());

		System.out.println(" User Info: " + url.getUserInfo());

	}

	/**
	 * Using The Network Interface Class
	 */
	private static void usingTheNetworkInterfaceClass() {

		try {

			Enumeration<NetworkInterface> interfaceEnum = NetworkInterface.getNetworkInterfaces();

			System.out.printf("Name     Display Name\n");

			for (NetworkInterface element : Collections.list(interfaceEnum)) {

				System.out.printf("%-8s  %-32s\n", element.getName(), element.getDisplayName());

				Enumeration<InetAddress> addresses = element.getInetAddresses();

				for (InetAddress inetAddress : Collections.list(addresses)) {

					System.out.printf("   InetAddress: %s\n", inetAddress);

				}

				// Functional Implementation
				addresses = element.getInetAddresses();

				Collections.list(addresses).stream().forEach((inetAddress) -> {

					System.out.printf("   InetAddress: %s\n", inetAddress);

				});

			}

//			interfaces = NetworkInterface.getNetworkInterfaces();
//
//			for (NetworkInterface element : Collections.list(interfaces)) {
//
//				displayInterfaceInformation(element);
//
//			}

		} catch (SocketException ex) {

			ex.printStackTrace();

		}

	}

	private static void gettingAMACAddress() {

		try {

			InetAddress address;

			address = InetAddress.getLocalHost();

			System.out.println("IP Address: " + address.getHostAddress());

			NetworkInterface network = NetworkInterface.getByInetAddress(address);

			System.out.println("MAC Address: " + getMACIdentifier(network));

			Enumeration<NetworkInterface> interfaceEnum = NetworkInterface.getNetworkInterfaces();

			System.out.println("Name    MAC Address");

//			for (NetworkInterface element : Collections.list(interfaceEnum)) {
//
//				System.out.printf("%-6s  %s\n", element.getName(), getMACIdentifier(element));
//
//			}

			// Java8 implementation
			System.out.println("--- Java 8 ---");

			interfaceEnum = NetworkInterface.getNetworkInterfaces();

			Collections.list(interfaceEnum).stream().forEach((inetAddress) -> {

				System.out.printf("%-6s  %s\n", inetAddress.getName(), getMACIdentifier(inetAddress));

			});

		} catch (UnknownHostException | SocketException ex) {

			ex.printStackTrace();

		}

	}

	private static String getMACIdentifier(NetworkInterface network) {

		StringBuilder sb = new StringBuilder();

		try {

			byte[] macBuffer = network.getHardwareAddress();

			if (macBuffer != null) {

				for (int i = 0; i < macBuffer.length; i++) {

					sb.append(String.format("%02X%s", macBuffer[i], (i < macBuffer.length - 1) ? "-" : ""));

				}

			} else {

				return "---";

			}

		} catch (SocketException ex) {

			ex.printStackTrace();
		}

		return sb.toString();

	}

}

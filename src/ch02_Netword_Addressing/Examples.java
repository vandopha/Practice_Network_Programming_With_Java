/******************************************************************************
 * Examples
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch02_Netword_Addressing;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class Examples {

	public static void main(String... args) {

//		usingTheNetworkInterfaceClass();
//		gettingAMACAddress();
//		creatingURIInstances();
//		UsingTheURLClass();
//		obtainingInformationAboutAnIPAddress();
//		testingReachability();
//		theInet4AddressClass();
//		testingForTheIPAddressType();
//		usingIPv4CompatibleIPv6Addresses();
//		isIPv4CompatibleAddressExample();

	}

	/**
	 * is IPv4 compatible with Address
	 */
	private static void isIPv4CompatibleAddressExample() {

		try {
			InetAddress names[] = InetAddress.getAllByName("www.google.com");
			for (InetAddress address : names) {
				if ((address instanceof Inet6Address) && ((Inet6Address) address).isIPv4CompatibleAddress()) {
					System.out.println(address + " is IPv4 Compatible Address");
				} else {
					System.out.println(address + " is not a IPv4 Compatible Address");
				}
			}

			// Java 8 implementation
			System.out.println("--- Java 8 Implementation ---");
			names = InetAddress.getAllByName("www.google.com");
			Arrays.stream(names).map(address -> {
				if ((address instanceof Inet6Address) && ((Inet6Address) address).isIPv4CompatibleAddress()) {
					return address + " is IPv4 Compatible Address";
				} else {
					return address + " is not IPv4 Compatible Address";
				}
			}).forEach(result -> System.out.println(result));
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * using IPv4 Compatible with IPv6 Address
	 */
	private static void usingIPv4CompatibleIPv6Addresses() {

		try {
			InetAddress address = InetAddress.getByName("www.packtpub.com");
			address = InetAddress.getByName("74.125.21.105");
			address = InetAddress.getByName("::fffff:74.125.21.105");
			address = InetAddress.getByName("::ffff:4a7d:1569");
			displayInetAddressInformation(address);
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * testing IP address type
	 */
	private static void testingForTheIPAddressType() {

		try {
			InetAddress address = InetAddress.getByName("www.packtpub.com");
			byte buffer[] = address.getAddress();
			if (buffer.length <= 4) {
				System.out.println("IPv4 Address");
			} else {
				System.out.println("IPv6 Address");
			}

			if (address instanceof Inet4Address) {
				System.out.println("IPv4 Address");
			} else {
				System.out.println("IPv6 Address");
			}
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Inet4Address class
	 */
	private static void theInet4AddressClass() {

		try {
			Inet4Address address;
			address = (Inet4Address) InetAddress.getByName("www.google.com");
			address = (Inet4Address) Inet4Address.getByName("www.google.com");

			// special IPv4 Addresses
			address = (Inet4Address) Inet4Address.getByName("0.0.0.0");
			System.out.println(address.isAnyLocalAddress());
			address = (Inet4Address) Inet4Address.getByName("127.0.0.1");
			System.out.println(address.isLoopbackAddress());
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * testing Reachability
	 */
	private static void testingReachability() {

		try {
			String URLAddress = "www.packtpub.com";
			InetAddress[] addresses = InetAddress.getAllByName(URLAddress);
			for (InetAddress inetAddress : addresses) {
				try {
					if (inetAddress.isReachable(10000)) {
						System.out.println(inetAddress + " is reachable");
					} else {
						System.out.println(inetAddress + " is unreachable");
					}
					Process p1 = java.lang.Runtime.getRuntime().exec("ping -n 1 " + URLAddress);
					int retVal = p1.waitFor();
					System.out.println(retVal);
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * obtain information about IP address
	 */
	private static void obtainingInformationAboutAnIPAddress() {

		try {
			InetAddress address = InetAddress.getByName("www.packtpub.com");
			displayInetAddressInformation(address);
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * display information
	 * 
	 * @param address
	 */
	private static void displayInetAddressInformation(InetAddress address) {

		System.out.println("---InetAddress Information---");
		System.out.println(address);
		System.out.println("CanonicalHostName: " + address.getCanonicalHostName());
		System.out.println("HostName: " + address.getHostName());
		System.out.println("HostAddress: " + address.getHostAddress());
		System.out.println("---------");

	}

	/**
	 * URL class
	 */
	private static void UsingTheURLClass() {

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
//			for (NetworkInterface element : Collections.list(interfaces)) {
//				displayInterfaceInformation(element);
//			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * getting a MAC address
	 */
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
//				System.out.printf("%-6s  %s\n", element.getName(), getMACIdentifier(element));
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

	/**
	 * get MAC identifier
	 * 
	 * @param network
	 * @return MAC identifier
	 */
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

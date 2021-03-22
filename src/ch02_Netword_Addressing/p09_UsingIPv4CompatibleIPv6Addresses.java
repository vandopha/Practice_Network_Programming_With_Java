/*******************************************************************************
 * Author: vandopha                                     		  
 * Date: Mar 22, 2021                                             
 * Description: Using IPv4 Compatible IPv6 Addresses
/******************************************************************************/
package ch02_Netword_Addressing;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class p09_UsingIPv4CompatibleIPv6Addresses {

	public static void main(String[] args) {

		usingIPv4CompatibleIPv6Addresses();

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

}

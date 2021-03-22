/******************************************************************************
 * Is IPv4 Compatible Address
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch02_Netword_Addressing;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class p10_IsIPv4CompatibleAddressExample {

	public static void main(String... args) {

		isIPv4CompatibleAddressExample();

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

}

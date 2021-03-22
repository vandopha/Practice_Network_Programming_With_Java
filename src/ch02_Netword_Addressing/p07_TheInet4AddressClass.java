/*******************************************************************************
 * Author: vandopha                                     		  
 * Date: Mar 22, 2021                                             
 * Description: The Inet4Address Class
/******************************************************************************/
package ch02_Netword_Addressing;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class p07_TheInet4AddressClass {

	public static void main(String[] args) {

		theInet4AddressClass();

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

}

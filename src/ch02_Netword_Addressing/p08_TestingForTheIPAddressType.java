/*******************************************************************************
 * Author: vandopha                                     		  
 * Date: Mar 22, 2021                                             
 * Description: Testing For The IP Address Type
/******************************************************************************/
package ch02_Netword_Addressing;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class p08_TestingForTheIPAddressType {

	public static void main(String[] args) {

		testingForTheIPAddressType();

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
}

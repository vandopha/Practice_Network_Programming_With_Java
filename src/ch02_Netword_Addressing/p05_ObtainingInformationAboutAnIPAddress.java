/*******************************************************************************
 * Author: vandopha                                     		  
 * Date: Mar 22, 2021                                             
 * Description: obtaining Information About An IP Address
/******************************************************************************/
package ch02_Netword_Addressing;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class p05_ObtainingInformationAboutAnIPAddress {

	public static void main(String[] args) {

		obtainingInformationAboutAnIPAddress();

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

}

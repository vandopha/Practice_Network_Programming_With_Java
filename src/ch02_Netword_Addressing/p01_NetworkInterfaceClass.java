/*******************************************************************************
 * Author: vandopha                                     		  
 * Date: Mar 22, 2021                                             
 * Description: Using Network Interface class
/******************************************************************************/
package ch02_Netword_Addressing;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class p01_NetworkInterfaceClass {

	public static void main(String[] args) {

		usingTheNetworkInterfaceClass();

	}

	/**
	 * Using The Network Interface Class
	 */
	private static void usingTheNetworkInterfaceClass() {

		try {
			Enumeration<NetworkInterface> interfaceEnum = NetworkInterface.getNetworkInterfaces();
			System.out.printf("Name      Display Name\n");
			for (NetworkInterface element : Collections.list(interfaceEnum)) {
				System.out.printf("%-8s  %-32s\n", element.getName(), element.getDisplayName());
				Enumeration<InetAddress> addresses = element.getInetAddresses();

				// traditional implementation
//				for (InetAddress inetAddress : Collections.list(addresses)) {
//					System.out.printf("   InetAddress: %s\n", inetAddress);
//				}

				// Java8 Implementation - Lambda
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

}

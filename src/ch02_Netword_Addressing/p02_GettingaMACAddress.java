/*******************************************************************************
 * Author: vandopha                                     		  
 * Date: Mar 22, 2021                                             
 * Description: getting a MAC address
/******************************************************************************/
package ch02_Netword_Addressing;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

public class p02_GettingaMACAddress {

	public static void main(String[] args) {

		gettingAMACAddress();

	}

	/**
	 * getting a MAC address
	 */
	private static void gettingAMACAddress() {

		try {
			// getting a MAC address
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("IP Address: " + address.getHostAddress());
			NetworkInterface network = NetworkInterface.getByInetAddress(address);
			System.out.println("MAC Address: " + getMACIdentifier(network));

			// getting multi MAC addresses
			Enumeration<NetworkInterface> interfaceEnum = NetworkInterface.getNetworkInterfaces();
			System.out.println("\nName    MAC Address");

			// traditional implementation
//			for (NetworkInterface element : Collections.list(interfaceEnum)) {
//				System.out.printf("%-6s  %s\n", element.getName(), getMACIdentifier(element));
//			}

			// Java8 implementation - Lambda
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

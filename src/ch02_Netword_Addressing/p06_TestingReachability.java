/*******************************************************************************
 * Author: vandopha                                     		  
 * Date: Mar 22, 2021                                             
 * Description: 
/******************************************************************************/
package ch02_Netword_Addressing;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class p06_TestingReachability {

	public static void main(String[] args) {

		testingReachability();

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

}

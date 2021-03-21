/******************************************************************************
 * Multicast Server
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch01_getting_started_with_network_programming;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class MulticastServer {

	public static void main(String[] args) {
		System.out.println("Multicast Time Server");
		DatagramSocket serverSocket = null;

		try {
			serverSocket = new DatagramSocket();
			while (true) {
				String dateText = new Date().toString();
				byte[] buffer = new byte[256];
				buffer = dateText.getBytes();

				InetAddress group = InetAddress.getByName("224.0.0.0");
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 8888);
				serverSocket.send(packet);
				System.out.println("Time sent " + dateText);

				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {
					// nothing
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

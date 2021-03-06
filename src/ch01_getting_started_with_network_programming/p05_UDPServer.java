/******************************************************************************
 * UDP Server
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch01_getting_started_with_network_programming;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class p05_UDPServer {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {

		final int serverPortNumber = 9876;

		try {
			DatagramSocket serverSocket = new DatagramSocket(serverPortNumber);

			while (true) {
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				System.out.println("Waiting for datagram packet");
				serverSocket.receive(receivePacket);
				String message = new String(receivePacket.getData());
				InetAddress address = receivePacket.getAddress();
				int clientPortNumber = receivePacket.getPort();

//              System.out.println("From: " + address + ":" + port);
				System.out.println("Message: " + message);

//              String capitalizedSentence = message.toUpperCase();
				byte[] sendData = message.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, clientPortNumber);
				serverSocket.send(sendPacket);
			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}

	}

}

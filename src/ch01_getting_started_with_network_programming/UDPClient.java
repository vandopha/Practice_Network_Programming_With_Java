/******************************************************************************
 * UDP (User Datagram Protocol)
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch01_getting_started_with_network_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPClient {

	public static void main(String[] args) throws Exception {

		final int portNumber = 9876;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				DatagramSocket clientSocket = new DatagramSocket();) {
			String serverHostname = "127.0.0.1";
			InetAddress address = InetAddress.getByName(serverHostname);

			System.out.print("Enter text: ");
//			String sentence = br.readLine();
			byte[] data = br.readLine().getBytes();
//			System.out.println("Sending data to " + sendData.length + " bytes to server.");
			DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, portNumber);
			clientSocket.send(sendPacket);

			byte[] receivedData = new byte[1024];
			DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
			System.out.println("Waiting for return packet");
			clientSocket.setSoTimeout(10000);

			try {
				InetAddress returnIpAddress = receivedPacket.getAddress();
				int port = receivedPacket.getPort();
				System.out.println("From server at: " + returnIpAddress + ": " + port);
//				System.out.println("Message: " + modifiedSentence);
				clientSocket.receive(receivedPacket);
				System.out.println("Message: " + new String(receivedPacket.getData()));
			} catch (SocketTimeoutException ex) {
				ex.printStackTrace();
			}
		}
	}

}

/******************************************************************************
 * Chat Client
 * @version 1.00 Mar 22, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class p02_Chat_Client {

	public static void main(String[] args) {

		new p02_Chat_Client();

	}

	@SuppressWarnings("resource")
	public p02_Chat_Client() {
		SocketAddress address = new InetSocketAddress("127.0.0.1", 6000);
		try (SocketChannel socketChannel = SocketChannel.open(address)) {
			System.out.println("Connected to Chat Server");
			String message;
			Scanner scanner = new Scanner(System.in);
			while (true) {
				// Receive message
				System.out.println("Waiting for message from the server ...");
				System.out.println("Message: " + HelperMethods.receiveMessage(socketChannel));
//				System.out.println("Message: " + HelperMethods.receiveFixedLengthMessage(socketChannel));
				System.out.print("> ");
				message = scanner.nextLine();
				if (message.equalsIgnoreCase("quit")) {
					HelperMethods.sendMessage(socketChannel, "Client terminating");
//					HelperMethods.sendFixedLengthMessage(socketChannel, "Client terminating");
					break;
				}
				// Send message
				HelperMethods.sendMessage(socketChannel, message);
//				HelperMethods.sendFixedLengthMessage(socketChannel, message);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}

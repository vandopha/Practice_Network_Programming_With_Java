/******************************************************************************
 * Chat Server 
 * @version 1.00 Mar 22, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class p02_Chat_Server {

	public static void main(String[] args) {

		new p02_Chat_Server();

	}

	@SuppressWarnings("resource")
	public p02_Chat_Server() {

		System.out.println("Chat Server started!!!");

		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(6000));

			boolean running = true;
			while (running) {
				System.out.println("Waiting for request...");
				SocketChannel socketChannel = serverSocketChannel.accept();

				System.out.println("Connected to Client");
				String message;
				Scanner scanner = new Scanner(System.in);
				while (true) {
					System.out.print("> ");
					message = scanner.nextLine();
					if (message.equalsIgnoreCase("quit")) {
//						HelperMethods.sendFixedLengthMessage(socketChannel, "Server terminating");
						HelperMethods.sendMessage(socketChannel, "Server terminating");
						running = false;
						break;
					} else {
//						HelperMethods.sendFixedLengthMessage(socketChannel, message);
						HelperMethods.sendMessage(socketChannel, message);
						// Receive Message
						System.out.println("Waiting for message from client...");
//						System.out.println("Message: " + HelperMethods.receiveFixedLengthMessage(socketChannel));
						System.out.println("Message: " + HelperMethods.receiveMessage(socketChannel));
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}

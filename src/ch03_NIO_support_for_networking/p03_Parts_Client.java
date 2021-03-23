/******************************************************************************
 * Parts Client
 * @version 1.00 Mar 22, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class p03_Parts_Client {

	public static void main(String[] args) {
		new p03_Parts_Client();
	}

	@SuppressWarnings("resource")
	public p03_Parts_Client() {
		System.out.println("PartsClient Started");
		SocketAddress address = new InetSocketAddress("127.0.0.1", 5000);
		try (SocketChannel socketChannel = SocketChannel.open(address)) {
			System.out.println("Connected to Parts Server");
			Scanner scanner = new Scanner(System.in);
			while (true) {
				// Get part name
				System.out.print("Enter part name: ");
				String partName = scanner.nextLine();
				if (partName.equalsIgnoreCase("quit")) {
					HelperMethods.sendMessage(socketChannel, "quit");
					break;
				} else {
					HelperMethods.sendMessage(socketChannel, partName);
					System.out.println("The price is " + HelperMethods.receiveMessage(socketChannel));
				}
			}
			System.out.println("PartsClient Terminated");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}

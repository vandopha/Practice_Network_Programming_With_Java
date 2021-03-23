/******************************************************************************
 * Client Handler
 * @version 1.00 Mar 22, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import java.nio.channels.SocketChannel;

public class ClientHandler implements Runnable {

	private final SocketChannel socketChannel;

	public ClientHandler(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public void run() {

		System.out.println("ClientHandler Started for " + this.socketChannel);
		String partName;
		while (true) {
			partName = HelperMethods.receiveMessage(socketChannel);
			if (partName.equalsIgnoreCase("quit")) {
				break;
			} else {
				Float price = p03_Parts_Server.getPrice(partName);
				HelperMethods.sendMessage(socketChannel, "" + price);
			}
		}
		System.out.println("ClientHandler Terminated for " + this.socketChannel);

	}

}

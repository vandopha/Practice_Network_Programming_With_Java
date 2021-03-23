/******************************************************************************
 * Parts Server
 * @version 1.00 Mar 22, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import static java.net.StandardSocketOptions.SO_RCVBUF;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class p03_Parts_Server {

	private static final HashMap<String, Float> parts = new HashMap<>();

	public p03_Parts_Server() {
		System.out.println("Part Server Started!");
		initializeParts();
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(5000));
			serverSocketChannel.setOption(SO_RCVBUF, 64);
			boolean running = true;
			while (running) {
				System.out.println("Waiting for client...");
				SocketChannel socketChannel = serverSocketChannel.accept();
				new Thread(new ClientHandler(socketChannel)).start();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void initializeParts() {

		parts.put("Hammer", 12.55f);
		parts.put("Nail", 1.35f);
		parts.put("Pliers", 4.65f);
		parts.put("Saw", 8.45f);

	}

	public static Float getPrice(String partName) {
		return parts.get(partName);
	}

	public static void main(String[] args) {
		new p03_Parts_Server();
	}

}

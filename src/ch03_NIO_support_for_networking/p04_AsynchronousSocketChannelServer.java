/******************************************************************************
 * Asynchronous Socket Channel Server
 * @version 1.00 Mar 23, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class p04_AsynchronousSocketChannelServer {

	@SuppressWarnings("rawtypes")
	public p04_AsynchronousSocketChannelServer() {

		System.out.println("Asynchronous Server Started");
		try (AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open()) {
			InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5000);
			serverChannel.bind(hostAddress);

			System.out.println("Waiting for client to connect ...");

			Future acceptResult = serverChannel.accept();

			try (AsynchronousSocketChannel clientChannel = (AsynchronousSocketChannel) acceptResult.get()) {
				System.out.println("Messages from client:");
				while ((clientChannel != null) && (clientChannel.isOpen())) {
					ByteBuffer buffer = ByteBuffer.allocate(32);
					Future result = clientChannel.read(buffer);

					// Technique 1
					while (!result.isDone()) {
						// do nothing
					}

					// Technique 2
//					result.get();

					// Technique 3
//					result.get(10, TimeUnit.SECONDS);
					buffer.flip();

					String message = new String(buffer.array()).trim();
					System.out.println(message);
					if (message.equals("quit")) {
						break;
					}
				}
			}
		} catch (IOException | InterruptedException | ExecutionException ex) {
			ex.printStackTrace();
//		} catch (TimeoutException ex) {
			// Used with get method when a timeout is specified
//			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new p04_AsynchronousSocketChannelServer();
	}

}

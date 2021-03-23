/******************************************************************************
 * Time Server with Channels
 * @version 1.00 Mar 22, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class p01_Time_server {

	public static void main(String[] args) {

		System.out.println("Time Server Started");

		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(5000));

			while (true) {
				System.out.println("Waiting for request ...");
				SocketChannel socketChannel = serverSocketChannel.accept();

				if (socketChannel != null) {
					String dateAndTimeMessage = "Date: " + new Date(System.currentTimeMillis());

					ByteBuffer buffer = ByteBuffer.allocate(64);
					// if buffer is not large enough: BufferOverflowException
					buffer.put(dateAndTimeMessage.getBytes());
					buffer.flip();
					while (buffer.hasRemaining()) {
						socketChannel.write(buffer);
					}

					System.out.println("Sent: " + dateAndTimeMessage);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}

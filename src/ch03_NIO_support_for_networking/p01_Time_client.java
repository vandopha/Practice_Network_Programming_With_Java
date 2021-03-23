/******************************************************************************
 * Time Client with Channels
 * @version 1.00 Mar 22, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class p01_Time_client {

	public static void main(String[] args) {

		SocketAddress address = new InetSocketAddress("127.0.0.1", 5000);

		try (SocketChannel socketChannel = SocketChannel.open(address)) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(64);
			int bytesRead = socketChannel.read(byteBuffer);

			while (bytesRead > 0) {
				byteBuffer.flip();

				while (byteBuffer.hasRemaining()) {
					System.out.print((char) byteBuffer.get());
				}

				System.out.println();
				bytesRead = socketChannel.read(byteBuffer);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}

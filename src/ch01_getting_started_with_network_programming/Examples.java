/******************************************************************************
 * Some network examples
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch01_getting_started_with_network_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;

public class Examples {
	public static void main(String[] args) {

		inetAddressExamples();

		nioExamples();

		socketExamples();

		urlConnectionExamples();

	}

	private static void inetAddressExamples() {

		System.out.println("--- Inet Address Example ---");

		try {

			InetAddress address = InetAddress.getByName("www.packtpub.com");

			System.out.println(address);

			displayInetAddressInformation(address);

			address = InetAddress.getByName("83.166.169.231");

			System.out.println(address);

			address = InetAddress.getLocalHost();

			System.out.println(address);

		} catch (UnknownHostException e) {

			e.printStackTrace();

		}

	}

	private static void displayInetAddressInformation(InetAddress address) {

		System.out.println("---InetAddress Information---");

		System.out.println(address);

		System.out.println("CanonicalHostName: " + address.getCanonicalHostName());

		System.out.println("HostAddress: " + address.getHostAddress());

		System.out.println("HostName: " + address.getHostName());

		System.out.println("---------");

	}

	private static void nioExamples() {

		System.out.println("--- Nio Example ---");

		try {

			InetAddress address = InetAddress.getByName("packtpub.com");

			SocketChannel socketChannel = SocketChannel.open();

			socketChannel.connect(new InetSocketAddress(address, 80));

			while (!socketChannel.finishConnect()) {

				// wait, or do something else

			}

			System.out.println(socketChannel);

			System.out.println(socketChannel.isConnected());

			System.out.println(socketChannel.isBlocking());

			ByteBuffer buffer = ByteBuffer.allocate(64);

			System.out.println("buffer: " + buffer);

			int bytesRead = socketChannel.read(buffer);

			System.out.println("bytes read: " + bytesRead);

			if (bytesRead > 0) {

				buffer.flip();

				while (buffer.hasRemaining()) {

					System.out.println("---" + buffer.get());

				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@SuppressWarnings("resource")
	private static void socketExamples() {

		System.out.println("--- Socket Example ---");

		try {

			String host = "google.com";

			InetAddress address = InetAddress.getByName(host);

			Socket socket = new Socket(address, 80);

			System.out.println(socket.isConnected());

			InputStream input = socket.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(input));

			while (!br.ready()) {

				// nothing here

			}

			String line = br.readLine();

			System.out.println("First - " + line);

			while ((line = br.readLine()) != null) {

				System.out.println("Each - " + line);

			}

			System.out.println("Done");

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@SuppressWarnings("unused")
	private static void urlConnectionExamples() {

		System.out.println("--- URL Connection Example ---");

		try {

			URL url = new URL("http://www.google.com");

//			URLConnection urlConnection = url.openConnection();

//			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

//			String line;

//			while ((line = br.readLine()) != null) {

//				System.out.println(line);

//			}

//			br.close();

			// channel
			System.out.println("Channel Example");

			URLConnection urlConnection = url.openConnection();

			InputStream inputStream = urlConnection.getInputStream();

			ReadableByteChannel channel = Channels.newChannel(inputStream);

			ByteBuffer buffer = ByteBuffer.allocate(64);

			String line = null;

			while (channel.read(buffer) > 0) {

				System.out.println("---" + new String(buffer.array()));

				buffer.clear();

			}

			channel.close();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}

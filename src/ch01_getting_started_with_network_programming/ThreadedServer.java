/******************************************************************************
 * Threaded Echo Server
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch01_getting_started_with_network_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ThreadedServer implements Runnable {

	private static Socket clientSocket;

	@SuppressWarnings("static-access")
	public ThreadedServer(Socket clientSocket) {

		this.clientSocket = clientSocket;

	}

	public static void main(String[] args) {

		System.out.println("--- Threaded Echo Server ---");

		try (ServerSocket serverSocket = new ServerSocket(6000)) {

			while (true) {

				System.out.println("Waiting for connection...");

				clientSocket = serverSocket.accept();

				ThreadedServer threadedServer = new ThreadedServer(clientSocket);

				new Thread(threadedServer).start();

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		System.out.println("Threaded Echo Server Terminating");

	}

	@Override
	public void run() {

		System.out.println("Connected to client using [" + Thread.currentThread() + "]");

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);) {

			// traditional implementation
//			String inputLine;

//			while ((inputLine = bufferedReader.readLine()) != null) {

//				System.out.println("Client request [" + Thread.currentThread() + "]:" + inputLine);

//				out.println(inputLine);

//			}

//			System.out.println("Client [" + Thread.currentThread() + "] connection terminated");

			// Functional implementation
			Supplier<String> socketInput = () -> {

				try {

					return bufferedReader.readLine();

				} catch (IOException ex) {

					return null;

				}

			};

			Stream<String> stream = Stream.generate(socketInput);

			stream.map(s -> {

				System.out.println("Client request: " + s);

				out.println(s);

				return s;

			}).allMatch(s -> s != null);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}

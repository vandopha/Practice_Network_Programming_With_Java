/******************************************************************************
 * Simple Echo Server
 * @version 1.00 Mar 20, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch01_getting_started_with_network_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class p01_SimpleEchoServer {

	public static void main(String[] args) {

		System.out.println("Simple Echo Server");

		try (ServerSocket serverSocket = new ServerSocket(6000)) {

			System.out.println("Waiting for connection...");

			Socket clientSocket = serverSocket.accept();

			System.out.println("Connected to client");

			try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

			) {

				// traditional implementation
				String inputLine;

				while ((inputLine = br.readLine()) != null) {

					System.out.println("Client request: " + inputLine);

					out.println(inputLine);

				}

				// functional implementation
//				Supplier<String> socketInput = () -> {

//					try {

//						return br.readLine();

//					} catch (IOException ex) {

//						return null;

//					}

//				};
//
//				Stream<String> stream = Stream.generate(socketInput);

//				stream.map(s -> {

//					System.out.println("Client request: " + s);

//					out.println(s);

//					return s;

//				}).allMatch(s -> s != null);

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		} catch (IOException ex) {

			ex.printStackTrace();

		}

		System.out.println("Simple Echo Server Terminating");

	}

}

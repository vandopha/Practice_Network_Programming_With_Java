/******************************************************************************
 * Simple Client
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch01_getting_started_with_network_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SimpleEchoClient {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Simple Echo CLient");

		try {
			System.out.println("Waiting for connection...");
			InetAddress localAddress = InetAddress.getLocalHost();

			try (Socket clientSocket = new Socket(localAddress, 6000);
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()))) {
				System.out.println("Connected to server");
				Scanner scanner = new Scanner(System.in);

				// traditional implementation
				while (true) {
					System.out.print("Enter text: ");
					String inputLine = scanner.nextLine();
					if ("quit".equalsIgnoreCase(inputLine)) {
						break;
					}
					out.println(inputLine);

					String response = bufferedReader.readLine();
					System.out.println("Server response: " + response);
				}

				// java8 implementation
//				Supplier<String> scannerInput = () -> scanner.nextLine();
//				System.out.print("Enter text: ");
//				Stream.generate(scannerInput).map(s -> {
//					out.print(s);
//					System.out.println("Server response: " + s);
//					System.out.print("Enter text: ");
//					return s;
//				}).allMatch(s -> !"quit".equalsIgnoreCase(s));
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

/******************************************************************************
 * SSL Socket Client
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch01_getting_started_with_network_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;

public class p04_SSLSocketClient {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		System.out.println("SSLClientSocket Started");
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try (Socket socket = sf.createSocket("localhost", 8000);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.print("Enter text: ");
				String inputLine = scanner.nextLine();
				if ("quit".equalsIgnoreCase(inputLine)) {
					break;
				}
				out.println(inputLine);
				System.out.println("Server response: " + br.readLine());
			}
			System.out.println("SSL Server Socket Terminated");
		}

	}

}

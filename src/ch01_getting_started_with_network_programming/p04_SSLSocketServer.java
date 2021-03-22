/******************************************************************************
 * SSL Socket Server
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

import javax.net.ssl.SSLServerSocketFactory;

public class p04_SSLSocketServer {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {

		try {

			SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

			ServerSocket serverSocket = new ServerSocket(8000);

			System.out.println("SSL Server Socket Started");

			try (Socket socket = serverSocket.accept();

					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

				System.out.println("Client socket created");

				String line = null;

				while ((line = br.readLine()) != null) {

					System.out.println(line);

					out.println(line);

				}

				br.close();

				System.out.println("SSL Server Socket terminated");

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}

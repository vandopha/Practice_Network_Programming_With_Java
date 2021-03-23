/******************************************************************************
 * Web Server
 * @version 1.00 Mar 23, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch04_Client_Server_Development;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class p01_Web_Server {

	/**
	 * Constructor
	 */
	public p01_Web_Server() {

		System.out.println("Web Server Started");
		try (ServerSocket serverSocket = new ServerSocket(80)) {
			while (true) {
				System.out.println("Waiting for client request");
				Socket remote = serverSocket.accept();
				System.out.println("Connection made");
				new Thread(new p01_Client_Handler(remote)).start();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) {

		new p01_Web_Server();

	}

}

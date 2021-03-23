/******************************************************************************
 * Simple HTTP CLient
 * @version 1.00 Mar 23, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch04_Client_Server_Development;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class p01_Simple_HTTP_Client {

	/**
	 * Constructor
	 */
	@SuppressWarnings("resource")
	public p01_Simple_HTTP_Client() {

		System.out.println("HTTP Client Started");
		try {
			InetAddress serverInetAddress = InetAddress.getByName("127.0.0.1");
			Socket connection = new Socket(serverInetAddress, 80);
			try (OutputStream out = connection.getOutputStream();
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));) {
				sendGet(out);
				System.out.println(getResponse(in));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * send GET query
	 * 
	 * @param out
	 */
	private void sendGet(OutputStream out) {

		try {
			// Send Request
			out.write("GET /index HTTP/1.0\r\n".getBytes());
			// Send Header
			out.write("User-Agent: Mozilla/5.0\r\n".getBytes());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Response
	 * 
	 * @param in
	 * @return
	 */
	private String getResponse(BufferedReader in) {

		try {
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine).append("\n");
			}
			return response.toString();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "";

	}

	public static void main(String[] args) {

		new p01_Simple_HTTP_Client();

	}

}

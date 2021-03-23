/******************************************************************************
 * Simple HTTP Server
 * @version 1.00 Mar 23, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch04_Client_Server_Development;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class p01_Simple_HTTP_Server {

	public static void main(String[] args) throws Exception {

		System.out.println("My HTTP Server Started");
		HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
		server.createContext("/index", new DetailHandler());
		server.setExecutor(Executors.newCachedThreadPool());
		server.setExecutor(Executors.newFixedThreadPool(5));
		server.start();

	}

	static class DetailHandler implements HttpHandler {

		/**
		 * handle
		 */
		@SuppressWarnings("rawtypes")
		@Override
		public void handle(HttpExchange exchange) throws IOException {

			// get request headers
			System.out.println("\nRequest Headers");
			Headers requestHeaders = exchange.getRequestHeaders();
			Set<String> keySet = requestHeaders.keySet();
			for (String key : keySet) {
				List values = requestHeaders.get(key);
				String header = key + "=" + values.toString() + "\n";
				System.out.print(header);
			}

			// process GET request
			String requestMethod = exchange.getRequestMethod();
			if (requestMethod.equalsIgnoreCase("GET")) {
				// process request body
				System.out.println("Request Body");
				InputStream inputStream = exchange.getRequestBody();
				if (inputStream != null) {
					try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
						String inputLine;
						StringBuilder response = new StringBuilder();
						while ((inputLine = bufferedReader.readLine()) != null) {
							response.append(inputLine);
						}
						bufferedReader.close();
						System.out.println(inputLine);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else {
					System.out.println("Request body is empty");
				}

				// manage response headers
				Headers responseHeaders = exchange.getResponseHeaders();

				// send response headers
				String responseMessage = getResponse();
				responseHeaders.set("Content-Type", "text/html");
				responseHeaders.set("Server", "SimpleHTTPServer/1.0");
				responseHeaders.set("Set-cookie", "userID=Cookie Monster");
				exchange.sendResponseHeaders(200, responseMessage.getBytes().length);

				System.out.println("Response Headers");
				Set<String> responseHeadersKeySet = responseHeaders.keySet();
				responseHeadersKeySet.stream().map((key) -> {
					List values = responseHeaders.get(key);
					String header = key + "=" + values.toString() + "\n";
					return header;
				}).forEach((header) -> {
					System.out.print(header);
				});

				// send message only
				try (OutputStream responseBody = exchange.getResponseBody()) {
					responseBody.write(responseMessage.getBytes());
				}
			}

		}

	}

	static class IndexHandler implements HttpHandler {

		/**
		 * handle
		 */
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			System.out.println(exchange.getRemoteAddress());

			String response = getResponse();
			exchange.sendResponseHeaders(200, response.length());

			OutputStream out = exchange.getResponseBody();
			out.write(response.getBytes());
			out.close();
		}

	}

	/**
	 * Reponse
	 * 
	 * @return a string of response
	 */
	public static String getResponse() {

		StringBuilder responseBuffer = new StringBuilder();
		responseBuffer.append("<html><h1>HTTPServer Home Page...</h1><br>")
				.append("<b>Welcome to the new and improved web server!</b><br>").append("</html>");

		return responseBuffer.toString();

	}

}

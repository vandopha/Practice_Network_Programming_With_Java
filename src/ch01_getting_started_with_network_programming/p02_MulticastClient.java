/******************************************************************************
 * Multicast Client
 * @version 1.00 Mar 21, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch01_getting_started_with_network_programming;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class p02_MulticastClient {

	public static void main(String[] args) {

		System.out.println("Multicast Time Client");

		try (MulticastSocket socket = new MulticastSocket(8888)) {

			InetAddress group = InetAddress.getByName("224.0.0.");

			socket.joinGroup(group);

			System.out.println("Multicast Group joined!");

			byte[] buffer = new byte[256];

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			for (int i = 0; i < 5; i++) {

				socket.receive(packet);

				String received = new String(packet.getData());

				System.out.println(received.trim());

			}

			socket.leaveGroup(group);

		} catch (IOException ex) {

			ex.printStackTrace();

		}

		System.out.println("Multicast Time Client Terminated");

	}

}

/******************************************************************************
 * Bulk Data Transfer with Buffer
 * @version 1.00 Mar 23, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import java.nio.IntBuffer;

public class p05_Bulk_data_transfer {

	public static void main(String[] args) {
		bulkDataTransfer();
	}

	private static void bulkDataTransfer() {

		int[] array = { 12, 51, 79, 54 };
		IntBuffer buffer = IntBuffer.allocate(6);
		System.out.println(buffer);

		buffer.put(array);
		System.out.println(buffer);
		displayBuffer(buffer);

		int length = buffer.remaining();
		buffer.put(array, 0, length);
		System.out.println(buffer);
		displayBuffer(buffer);

	}

	private static void displayBuffer(IntBuffer buffer) {

		int arr[] = new int[buffer.position()];
		buffer.rewind();
		buffer.get(arr);
		for (int element : arr) {
			System.out.print(element + " ");
		}
		System.out.println();
		for (int i = 0; i < buffer.position(); i++) {
			System.out.print(buffer.get(i) + " ");
		}
		System.out.println();

	}

}

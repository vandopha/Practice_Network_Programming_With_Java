/******************************************************************************
 * Creating Buffers
 * @version 1.00 Mar 23, 2021                                             
 * @author vandopha
/******************************************************************************/

package ch03_NIO_support_for_networking;

import java.nio.ByteBuffer;

public class p06_Creating_Buffers {

	public static void main(String[] args) {

		creatingBuffers();

	}

	private static void creatingBuffers() {

		String contents = "Book";
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.put(contents.getBytes());

		// Views
		ByteBuffer duplicateBuffer = buffer.duplicate();
		duplicateBuffer.put(0, (byte) 0x4c); // 'L'
		System.out.println("buffer: " + buffer.get(0));
		System.out.println("duplicateBuffer: " + duplicateBuffer.get(0));
		System.out.println();

		// Creating a read-only buffer
		ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
		System.out.println("Read-only: " + readOnlyBuffer.isReadOnly());

	}

}

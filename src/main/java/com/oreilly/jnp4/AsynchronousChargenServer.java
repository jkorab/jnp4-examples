package com.oreilly.jnp4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.Future;

// FIXME!!
public class AsynchronousChargenServer {

    public static int DEFAULT_PORT = 19;

    public static void main(String[] args) throws Exception {

        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (RuntimeException ex) {
            port = DEFAULT_PORT;
        }
        System.out.println("Listening for connections on port " + port);

        byte[] rotation = new byte[95 * 2];
        for (byte i = ' '; i <= '~'; i++) {
            rotation[i - ' '] = i;
            rotation[i + 95 - ' '] = i;
        }

        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(port);
        serverChannel.bind(address);

        while (true) {
            Future<AsynchronousSocketChannel> future = serverChannel.accept();
            AsynchronousSocketChannel channel = future.get();

            // channel.w
/**

            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            System.out.println("Accepted connection from " + client);
            client.configureBlocking(false);
            SelectionKey key2 = client.register(selector, SelectionKey.
                    OP_WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(74);
            buffer.put(rotation, 0, 72);
            buffer.put((byte) '\r');
            buffer.put((byte) '\n');
            buffer.flip();
            key2.attach(buffer);
        } else if (key.isWritable()) {
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            if (!buffer.hasRemaining()) {
                // Refill the buffer with the next line
                buffer.rewind();
                // Get the old first character
                int first = buffer.get();
                // Get ready to change the data in the buffer
                buffer.rewind();
                // Find the new first characters position in rotation
                int position = first - ' ' + 1;
                // copy the data from rotation into the buffer
                buffer.put(rotation, position, 72);
                // Store a line break at the end of the buffer
                buffer.put((byte) '\r');
                buffer.put((byte) '\n');
                // Prepare the buffer for writing
                buffer.flip();
            }
            client.write(buffer);
*/          break; // TODO remove
        }
    }
}
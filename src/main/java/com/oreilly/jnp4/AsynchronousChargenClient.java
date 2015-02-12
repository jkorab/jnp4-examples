package com.oreilly.jnp4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class AsynchronousChargenClient {

    private static class LineHandler implements
            CompletionHandler<Integer, ByteBuffer> {

        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            buffer.flip();
            WritableByteChannel out = Channels.newChannel(System.out);
            try {
                out.write(buffer);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }

        @Override
        public void failed(Throwable ex, ByteBuffer attachment) {
            System.err.println(ex.getMessage());
        }

    }

    public static int DEFAULT_PORT = 19;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java com.oreilly.jnp4.ChargenClient host [port]");
            return;
        }

        int port;
        try {
            port = Integer.parseInt(args[1]);
        } catch (RuntimeException ex) {
            port = DEFAULT_PORT;
        }

        SocketAddress address = new InetSocketAddress(args[0], port);
        try {
            AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
            client.connect(address);

            while (true) {
                ByteBuffer buffer = ByteBuffer.allocate(74);
                CompletionHandler<Integer, ByteBuffer> handler = new LineHandler();
                try {
                    client.read(buffer, buffer, handler);
                } catch (ReadPendingException ex) {
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
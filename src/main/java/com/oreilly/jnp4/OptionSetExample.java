package com.oreilly.jnp4;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.NetworkChannel;
import java.nio.channels.SocketChannel;

public class OptionSetExample {

    public static void main(String[] args) throws IOException {
        NetworkChannel channel = SocketChannel.open();
        channel.setOption(StandardSocketOptions.SO_LINGER, 240);
    }

}

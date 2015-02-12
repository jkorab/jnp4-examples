package com.oreilly.jnp4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer extends UDPServer {

    public final static int DEFAULT_PORT = 7;

    public UDPEchoServer() {
        super(DEFAULT_PORT);
    }

    @Override
    public void respond(DatagramSocket socket, DatagramPacket packet)
            throws IOException {
        DatagramPacket outgoing = new DatagramPacket(packet.getData(),
                packet.getLength(), packet.getAddress(), packet.getPort());
        socket.send(outgoing);
    }

    public static void main(String[] args) {
        UDPServer server = new UDPEchoServer();
        Thread t = new Thread(server);
        t.start();
    }
}
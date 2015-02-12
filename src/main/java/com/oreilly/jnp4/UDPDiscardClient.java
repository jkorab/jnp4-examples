package com.oreilly.jnp4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDiscardClient {

    public final static int PORT = 9;

    public static void main(String[] args) {

        String hostname = args.length > 0 ? args[0] : "localhost";

        try (DatagramSocket theSocket = new DatagramSocket()) {
            InetAddress server = InetAddress.getByName(hostname);
            BufferedReader userInput
                    = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String theLine = userInput.readLine();
                if (theLine.equals(".")) break;
                byte[] data = theLine.getBytes();
                DatagramPacket theOutput
                        = new DatagramPacket(data, data.length, server, PORT);
                theSocket.send(theOutput);
            } // end while
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
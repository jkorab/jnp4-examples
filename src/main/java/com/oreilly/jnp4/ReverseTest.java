package com.oreilly.jnp4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReverseTest {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getByName("208.201.239.100");
        System.out.println(ia.getCanonicalHostName());
    }
}

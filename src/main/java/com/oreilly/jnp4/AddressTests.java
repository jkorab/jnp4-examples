package com.oreilly.jnp4;

import java.net.InetAddress;

public class AddressTests {

    public static int getVersion(InetAddress ia) {
        byte[] address = ia.getAddress();
        if (address.length == 4) return 4;
        else if (address.length == 16) return 6;
        else return -1;
    }
}
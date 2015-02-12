package com.oreilly.jnp4;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class URLPrinter {

    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.oreilly.com/");
            URLConnection uc = u.openConnection();
            System.out.println(uc.getURL());
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
package com.oreilly.jnp4;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;


public class SafeBufferedReaderTest {

    private static class MockReader extends Reader {

        private boolean read = false;

        @Override
        public int read(char[] dest, int off, int length) throws IOException {
            if (!read) {
                read = true;
                char[] data = "Hello\rGoodbye\r".toCharArray();
                int min = Math.min(data.length, length);
                System.arraycopy(data, 0, dest, off, min);
                return min;
            }

            return 0;
        }

        @Override
        public void close() throws IOException {
        }

    }

    @Test @Ignore
    public void testRead() throws IOException {
        String s = "Hello\r\nGoodbye";
        SafeBufferedReader reader = new SafeBufferedReader(new StringReader(s));
        assertEquals("Hello", reader.readLine());
        assertEquals('G', reader.read());
        reader.close();
    }

    private static class MockInputStream extends InputStream {

        private int counter = 0;

        @Override
        public int available() {
            return counter - 4;
        }

        @Override
        public int read() throws IOException {
            switch (counter) {
                case 0:
                    System.out.println(counter);
                    counter++;
                    return 'H';
                case 1:
                    System.out.println(counter);
                    counter++;
                    return '\r';
                case 2:
                    System.out.println(counter);
                    counter++;
                    return 'J';
                case 3:
                    System.out.println(counter);
                    counter++;
                    return '\n';
                default:
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return -1;
            }
        }
    }

    @Test @Ignore
    public void testReadLine() throws IOException {
        BufferedReader reader = new BufferedReader(new MockReader());
        assertEquals("Hello", reader.readLine());
        assertEquals("Goodbye", reader.readLine());
        assertEquals("", reader.readLine());

        reader.close();
    }

}

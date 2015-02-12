package com.oreilly.jnp4;

import java.io.*;

public class SafePrintWriter extends Writer {

    protected Writer out;

    private boolean autoFlush = false;
    private String lineSeparator;
    private boolean closed = false;

    public SafePrintWriter(Writer out, String lineSeparator) {
        this(out, false, lineSeparator);
    }

    public SafePrintWriter(Writer out, char lineSeparator) {
        this(out, false, String.valueOf(lineSeparator));
    }

    public SafePrintWriter(Writer out, boolean autoFlush, String lineSeparator) {
        super(out);
        this.out = out;
        this.autoFlush = autoFlush;
        if (lineSeparator == null) {
            throw new NullPointerException("Null line separator");
        }
        this.lineSeparator = lineSeparator;
    }

    public SafePrintWriter(OutputStream out, boolean autoFlush,
                           String encoding, String lineSeparator)
            throws UnsupportedEncodingException {
        this(new OutputStreamWriter(out, encoding), autoFlush, lineSeparator);
    }

    public void flush() throws IOException {
        synchronized (lock) {
            if (closed) throw new IOException("Stream closed");
            out.flush();
        }
    }

    public void close() throws IOException {
        try {
            this.flush();
        } catch (IOException ex) {
        }

        synchronized (lock) {
            out.close();
            this.closed = true;
        }
    }

    public void write(int c) throws IOException {
        synchronized (lock) {
            if (closed) throw new IOException("Stream closed");
            out.write(c);
        }
    }

    public void write(char[] text, int offset, int length) throws IOException {
        synchronized (lock) {
            if (closed) throw new IOException("Stream closed");
            out.write(text, offset, length);
        }
    }

    public void write(char[] text) throws IOException {
        synchronized (lock) {
            if (closed) throw new IOException("Stream closed");
            out.write(text, 0, text.length);
        }
    }

    public void write(String s, int offset, int length) throws IOException {
        synchronized (lock) {
            if (closed) throw new IOException("Stream closed");
            out.write(s, offset, length);
        }
    }

    public void print(boolean b) throws IOException {
        if (b) this.write("true");
        else this.write("false");
    }

    public void println(boolean b) throws IOException {
        synchronized (lock) {
            this.print(b);
            this.write(lineSeparator);
        }
        if (autoFlush) out.flush();
    }

    public void print(char c) throws IOException {
        this.write(String.valueOf(c));
    }

    public void println(char c) throws IOException {
        synchronized (lock) {
            this.print(c);
            this.write(lineSeparator);
        }
        if (autoFlush) out.flush();
    }

    public void print(int i) throws IOException {
        this.write(String.valueOf(i));
    }

    public void println(int i) throws IOException {
        synchronized (lock) {
            this.print(i);
            this.write(lineSeparator);
        }
        if (autoFlush) out.flush();
    }

    public void print(long l) throws IOException {
        this.write(String.valueOf(l));
    }

    public void println(long l) throws IOException {
        synchronized (lock) {
            this.print(l);
            this.write(lineSeparator);
        }
        if (autoFlush) out.flush();
    }

    public void print(float f) throws IOException {
        this.write(String.valueOf(f));
    }

    public void println(float f) throws IOException {
        synchronized (lock) {
            this.print(f);
            this.write(lineSeparator);
        }
        if (autoFlush) out.flush();
    }

    public void print(double d) throws IOException {
        this.write(String.valueOf(d));
    }

    public void println(double d) throws IOException {
        synchronized (lock) {
            this.print(d);
            this.write(lineSeparator);
        }
        if (autoFlush) out.flush();
    }

    public void print(char[] text) throws IOException {
        this.write(text);
    }

    public void println(char[] text) throws IOException {
        synchronized (lock) {
            this.print(text);
            this.write(lineSeparator);
        }
        if (autoFlush) out.flush();
    }

    public void print(String s) throws IOException {
        if (s == null) this.write("null");
        else this.write(s);
    }

    public void println(String s) throws IOException {
        synchronized (lock) {
            this.print(s);
            this.write(lineSeparator);
        }
        if (autoFlush) out.flush();
    }

    public void print(Object o) throws IOException {
        if (o == null) this.write("null");
        else this.write(o.toString());
    }

    public void println(Object o) throws IOException {
        synchronized (lock) {
            this.print(o);
            this.write(lineSeparator);
        }
        if (autoFlush) out.flush();
    }

    public void println() throws IOException {
        this.write(lineSeparator);
        if (autoFlush) out.flush();
    }
}
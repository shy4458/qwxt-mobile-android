package com.sx.baseframework.util;

import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

/**
 * I/O operation utility methods.
 *
 * @author JadynZhang
 * @version 1.0.0
 */
public final class IOUtils {

    private IOUtils() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect. Ignore the I/O error.
     *
     * @param closeables Closeables
     */
    public static void close(Closeable... closeables) {
        if(closeables == null) {
            return;
        }
        for(Closeable closeable : closeables) {
            if(closeable != null) {
                try {closeable.close();} catch (IOException ignore) {}
            }
        }
    }

    /**
     * Flushes this output stream, and then close this output stream.
     * <p>Ignore the I/O error.
     *
     * @param os OutputStream
     */
    public static void flushAndClose(OutputStream os) {
        if(os != null) {
            try {
                os.flush();
                os.close();
            } catch (IOException ignore) {}
        }
    }

    /**
     * Flushes this stream, and then close this stream.
     * <p>Ignore the I/O error.
     *
     * @param writer Writer
     */
    public static void flushAndClose(Writer writer) {
        if(writer != null) {
            try {
                writer.flush();
                writer.close();
            } catch (IOException ignore) {}
        }
    }

    /**
     * Reads the input stream to a bytes array.
     *
     * @param in InputStream
     * @return bytes array containing all data.
     * @throws IOException If an I/O error occurs when reading.
     */
    public static byte[] readAllToBytes(@NonNull InputStream in) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        byte[] stuff = new byte[1024];
        while ( (read = in.read(stuff)) != -1) {
            buffer.write(stuff, 0, read);
        }
        in.close();
        return buffer.toByteArray();
    }

}

package fortnitereplayreader.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * A java class that is capable for parsing .net BinaryWriter encoded files.
 * Some methods are not implemented as I have not need for them. Enjoy!
 * @author robbiev
 *
 */
public class BinaryReader extends FilterInputStream {

    private long pos = 0;
    private long mark = 0;

    public BinaryReader(InputStream in) {
        super(in);
    }

    public long readInt64() throws IOException{
        return ByteBuffer.wrap(this.readBytes(8)).order(ByteOrder.LITTLE_ENDIAN).getLong();
    }

    public char readChar() throws IOException{
        return ByteBuffer.wrap(this.readBytes(2)).order(ByteOrder.LITTLE_ENDIAN).getChar();
    }

    public double readDouble() throws IOException{
        return ByteBuffer.wrap(this.readBytes(8)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
    }

    public BigDecimal readDecimal() throws IOException{
        return new BigDecimal(new BigInteger(1, this.readBytes(16)));
    }

    public BigInteger readUInt64() throws IOException{
        return new BigInteger(1, this.readBytes(8));
    }


    /**
     * Reads a 4-byte signed integer from the current stream and advances the current position of the stream by four bytes.
     * @return
     * @throws IOException
     */
    public int readInt32() throws IOException {
        return ByteBuffer.wrap(this.readBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }


    /**
     * Reads a 4-byte unsigned integer from the current stream and advances the position of the stream by four bytes. Returns a long as java does not have the means to have unsigned values.
     * @return
     * @throws IOException
     */
    public long readUInt32() throws IOException {
        return this.readInt32() & 0xFFFFFFFFL;
    }

    /**
     * Reads a 2-byte signed integer from the current stream and advances the current position of the stream by two bytes. Returns a 32-bit int as there are not 16-bit ints in java.
     * @return
     * @throws IOException
     */
    public int readInt16() throws IOException {
        return ByteBuffer.wrap(this.readBytes(2)).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    /**
     * Reads a 2-byte unsigned integer from the current stream using little-endian encoding and advances the position of the stream by two bytes. Returns a 32-bit int as there are not 16-bit ints in java.
     * @return
     * @throws IOException
     */
    public int readUInt16() throws IOException {
        return this.readInt16() & 0xFFFF;
    }

    /**
     * Reads a string from the current stream. The string is prefixed with the length, encoded as an integer seven bits at a time.
     * @return
     * @throws IOException
     */
    public String readString() throws IOException {
        return new String(this.readBytes(this.getStringLength()));
    }

    /**
     * Reads a Boolean value from the current stream and advances the current position of the stream by one byte.
     * @return
     * @throws IOException
     */
    public boolean readBoolean() throws IOException {
        return this.readBytes(1)[0] != 0;
    }

    /**
     * Reads a 4-byte floating point value from the current stream and advances the current position of the stream by four bytes.
     * @return
     * @throws IOException
     */
    public float readSingle() throws IOException {
        return ByteBuffer.wrap(this.readBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    /**
     * Source: https://github.com/vrecan/Thaw-Giant/blob/master/src/main/java/com/vreco/thawgiant/BinaryUtil.java
     * Binary files are encoded with a variable length prefix that tells you
     * the size of the string. The prefix is encoded in a 7bit format where the
     * 8th bit tells you if you should continue. If the 8th bit is set it means
     * you need to read the next byte.
     * @return
     * @throws IOException
     */
    private int getStringLength() throws IOException {
        int count = 0;
        int shift = 0;
        boolean more = true;
        while (more) {
            byte b = (byte) this.read();
            count |= (b & 0x7F) << shift;
            shift += 7;
            if((b & 0x80) == 0) {
                more = false;
            }
        }
        if(count < 0) throw new IOException("String length cant be lower than 0. Thats impossibru.");
        return count;
    }

    /**
     * Read an arbitrary number of bytes.
     * @param length
     * @return
     * @throws IOException
     */
    public byte[] readBytes(int length) throws IOException {
        byte[] bytes = new byte[length];
        //this.read(bytes);

        pos += this.read(bytes);
        //System.out.println(pos);

        return bytes;
    }

    public char[] readChars(int length) throws IOException{
        return ByteBuffer.wrap(this.readBytes( length * 2 )).asCharBuffer().array();
    }

    public String readFString() throws Exception {
        int length = this.readInt32();
        boolean isUnicode = length < 0;

        if (isUnicode) {
            length = -length;
        }

        if (length < 0) {
            throw new Exception("Archive is corrupted");
        }

        byte[] data;
        String value;

        if(isUnicode) {
            data = this.readBytes(length * 2);
            value = new String(data, "UTF8");
        } else {
            data = this.readBytes(length);
            value = new String(data, "UTF8");
        }

        return value.trim().replaceAll("\0", "");
    }

    @Override
    public synchronized long skip(long skip) throws IOException {
        long n = super.skip(skip);
        if (n > 0)
            pos += n;
        return n;
    }


    public long getPosition() {
        return pos;
    }
}

package pl.edu.pw.ee.lzw;

import java.io.*;
import java.util.*;

public class LZWCompression {

    // Define a HashMap and other variables that will be used in the program
    public HashMap<String, Integer> dictionary = new HashMap<>();
    public int dictSize = 256;
    public String str = "";
    public byte inputByte;
    public byte[] buffer = new byte[3];
    public boolean onleft = true;

    /**
     * Takes in a file name that is uncompressed, and will compress it's file
     * contents and append a ".lzw" to the end of the current file name
     *
     * @param uncompressed - Name of uncompressed file being compressed
     * @throws java.io.IOException - File input/output failure
     */
    public void compress(String uncompressed, String compressed) throws IOException {
        // Dictionary size limit, builds dictionary
        for (int i = 0; i < 256; i++) {
            dictionary.put(Character.toString((char) i), i);
        }

        // Read input uncompress file & Write out compressed file
        RandomAccessFile read = new RandomAccessFile(uncompressed, "r");
        RandomAccessFile out = new RandomAccessFile(compressed, "rw");

        try {
            // Reads the First Character from input file into the String
            inputByte = read.readByte();
            int i = new Byte(inputByte).intValue();
            if (i < 0) {
                i += 256;
            }
            char ch = (char) i;
            str = "" + ch;

            // Reads Character by Character
            while (true) {
                inputByte = read.readByte();
                i = new Byte(inputByte).intValue();

                if (i < 0) {
                    i += 256;
                }
               // System.out.print(i + ", ");
                ch = (char) i;

                // If str + ch is in the dictionary..
                // Set str to str + ch
                if (dictionary.containsKey(str + ch)) {
                    str = str + ch;
                } else {
                    String s12 = to12bit(dictionary.get(str));

                    // Store the 12 bits into an array and then write it to the
                    // output file
                    if (onleft) {
                        buffer[0] = (byte) Integer.parseInt(
                                s12.substring(0, 8), 2);
                        buffer[1] = (byte) Integer.parseInt(
                                s12.substring(8, 12) + "0000", 2);
                    } else {
                        buffer[1] += (byte) Integer.parseInt(
                                s12.substring(0, 4), 2);
                        buffer[2] = (byte) Integer.parseInt(
                                s12.substring(4, 12), 2);
                        for (int b = 0; b < buffer.length; b++) {
                            System.out.println(buffer[b]);
                            out.writeByte(buffer[b]);
                            buffer[b] = 0;
                        }
                    }
                    onleft = !onleft;

                    // Add str + ch to the dictionary
                    if (dictSize < 4096) {
                        dictionary.put(str + ch, dictSize++);
                    }

                    // Set str to ch
                    str = "" + ch;
                }
            }
            /**
             * Handles input/output file failure by converting 8bit to 12bit
             * then storing integers to byte and writing to output file else add
             * the buffers to [1] or use buffer[2] then using the length and a
             * for loop to output the bytes and then zero out the buffer, note
             * this code is similar to above code, which insures bits are stored
             */
        } catch (IOException e) {
            String str12bit = to12bit(dictionary.get(str));
            if (onleft) {
                buffer[0] = (byte) Integer.parseInt(str12bit.substring(0, 8), 2);
                buffer[1] = (byte) Integer.parseInt(str12bit.substring(8, 12)
                        + "0000", 2);
                out.writeByte(buffer[0]);
                out.writeByte(buffer[1]);
            } else {
                buffer[1] += (byte) Integer.parseInt(str12bit.substring(0, 4), 2);
                buffer[2] = (byte) Integer.parseInt(str12bit.substring(4, 12), 2);

                for (int b = 0; b < buffer.length; b++) {
                    out.writeByte(buffer[b]);
                    buffer[b] = 0;
                }
            }
            read.close();
            out.close();
        }
    }

    /**
     * Converts 8 bits to 12 bits
     *
     * @param i - Integer value
     * @return - String value of integer in 12 bit
     */
    public String to12bit(int i) {
        String str = Integer.toBinaryString(i);
        while (str.length() < 12) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * After creating a lzw object scans user input for file to compress, and
     * prints out contents of file being compressed along with integer values of
     * the characters being compressed, and will return your file name with an
     * appended ".lzw"
     *
     * @param args - The command line arguments
     * @throws java.io.IOException - File input/output failure
     */
    public static void invoke(String in, String out){
        try {
            LZWCompression lzw = new LZWCompression();
            lzw.compress(in, out);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (IOException e){
            System.out.println("IOException occured.");
        }
    }
}
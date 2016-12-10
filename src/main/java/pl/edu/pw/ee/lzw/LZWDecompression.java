package pl.edu.pw.ee.lzw;

import java.io.*;
import java.util.*;

public class LZWDecompression {

    // Define a HashMap and other variables that will be used in the program
    public HashMap<Integer, String> dictionary = new HashMap<>();
    public String[] charArray;
    public int dictSize = 256;
    public int currword;
    public int priorword;
    public byte[] buffer = new byte[3];
    public boolean onleft = true;

    /**
     * Decompress input file, write it content to output file.
     * 
     * @param input - Name of input file path
     * @param output - name of output file
     * @throws java.io.IOException - File input/output failure
     */
    public void decompress(String input, String output) throws IOException {
        // DictSize builds up to 4k, Array_Char holds these values
        charArray = new String[4096];

        for (int i = 0; i < 256; i++) {
            dictionary.put(i, Character.toString((char) i));
            charArray[i] = Character.toString((char) i);
        }

        // Read input as uncompressed file & Write out compressed file
        RandomAccessFile in = new RandomAccessFile(input, "r");
        RandomAccessFile out = new RandomAccessFile(output, "rw");

        try {
            // Gets the first word in code and outputs its corresponding char
            buffer[0] = in.readByte();
            buffer[1] = in.readByte();
            priorword = getvalue(buffer[0], buffer[1], onleft);
            onleft = !onleft;
            out.writeBytes(charArray[priorword]);

            // Reads every 3 bytes and generates corresponding characters
            while (true) {
                if (onleft) {
                    buffer[0] = in.readByte();
                    buffer[1] = in.readByte();
                    currword = getvalue(buffer[0], buffer[1], onleft);
                } else {
                    buffer[2] = in.readByte();
                    currword = getvalue(buffer[1], buffer[2], onleft);
                }
                onleft = !onleft;

                if (currword >= dictSize) {
                    if (dictSize < 4096) {
                        charArray[dictSize] = charArray[priorword]
                                + charArray[priorword].charAt(0);
                    }
                    dictSize++;
                    out.writeBytes(charArray[priorword]
                            + charArray[priorword].charAt(0));
                } else {
                    if (dictSize < 4096) {
                        charArray[dictSize] = charArray[priorword]
                                + charArray[currword].charAt(0);
                    }
                    dictSize++;
                    out.writeBytes(charArray[currword]);
                }
                priorword = currword;
            }
        } catch (EOFException e) {
            in.close();
            out.close();
        }
    }

    /**
     * Extract the 12 bit key from 2 bytes and gets the integer value of the key
     *
     * @param b1 - First byte
     * @param b2 - Second byte
     * @param onleft - True if on left, false if not
     * @return - An Integer which holds the value of the key
     */
    public int getvalue(byte b1, byte b2, boolean onleft) {
        String temp1 = Integer.toBinaryString(b1);
        String temp2 = Integer.toBinaryString(b2);

        while (temp1.length() < 8) temp1 = "0" + temp1;
        
        if (temp1.length() == 32) temp1 = temp1.substring(24, 32);
        
        while (temp2.length() < 8) temp2 = "0" + temp2;
        
        if (temp2.length() == 32) temp2 = temp2.substring(24, 32);
        

        return onleft ? 
                Integer.parseInt(temp1 + temp2.substring(0, 4), 2) :
                Integer.parseInt(temp1.substring(4, 8) + temp2, 2);

    }

    /**
     * Creating a LZWDecompression object, starts decompressing.
     *
     * @param in - input file name
     * @param out - output file name
     */
    public static void invoke(String in, String out){
        try {
            LZWDecompression lzw = new LZWDecompression();
            lzw.decompress(in,out);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (IOException e){
            System.out.println("IOException occured.");
        }
    }
}
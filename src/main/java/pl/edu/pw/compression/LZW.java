package pl.edu.pw.compression;


import pl.edu.pw.compression.inter.LZWDecompressionImpl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Serdecznie polecam https://pl.wikipedia.org/wiki/LZW
 * Created by clutroth on 01.12.16.
 */
public class LZW {

    public OutputStream decompress(InputStream is, OutputStream os, byte[] dictionary) {
        LZWDecompression dec = LZWDecompressionImpl.begin("abcd".getBytes(), 4);
        LZWDecompression nowszy = dec.put(2);

        List<Byte> zakodowanaWartośćDoZapisu = (List<Byte>) nowszy.value(); //To jest optional, tłumaczony na listę
        LZWDecompression jeszczenowszy = dec.put(33);
        LZWDecompression jeszczejeszczenowszy = dec.put(62);
        LZWDecompression jeszczejeszczejeszczenowszy = dec.put(82);


        return null;
    }

    public OutputStream compress(InputStream is, OutputStream os, byte[] dictionary) {
        return null;
    }

}

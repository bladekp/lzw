package pl.edu.pw.compression.inter;

import pl.edu.pw.compression.LZW2;
import scala.collection.immutable.List;

/**
 * Created by clutroth on 30.11.16.
 */
public class cxz {
    public void dos() {
        List<Object> a = LZW2.compress("abcabca", "abc".getBytes());

    }
}

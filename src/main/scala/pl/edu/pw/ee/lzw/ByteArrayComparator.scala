package pl.edu.pw.ee.lzw

import java.util.Comparator

/**
  * Created by clutroth on 16.11.16.
  */
class ByteArrayComparator extends Ordering[Array[Byte]] {
  override def compare(t: Array[Byte], t1: Array[Byte]): Int = {
    def toString(b: Array[Byte])=
      new String(b.map (_.toChar))
    toString(t).compareTo(toString(t1))
  }
}

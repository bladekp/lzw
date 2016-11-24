package pl.edu.pw.ee.lzw
import ByteListComparator._

/**
  * Created by clutroth on 21.11.16.
  */
 class ByteListComparator extends   Ordering[List[Byte]] {

  override def compare(x: List[Byte], y: List[Byte]): Int =
   arrayComparator.compare(x.toArray, y.toArray)
}
object  ByteListComparator{
 val arrayComparator = new ByteArrayComparator
}

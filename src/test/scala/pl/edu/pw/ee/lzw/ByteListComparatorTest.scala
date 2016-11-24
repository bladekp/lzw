package pl.edu.pw.ee.lzw

import org.junit.Test
import org.junit.Assert._

/**
  * Created by clutroth on 21.11.16.
  */
class ByteListComparatorTest {
  val big = List[Byte](1, 2)
  val small = List[Byte](1, 1)
  val comp: Ordering[List[Byte]] = new ByteListComparator

  @Test def testListComparator = {
        assertEquals(1, comp.compare(big, small))
  }
}

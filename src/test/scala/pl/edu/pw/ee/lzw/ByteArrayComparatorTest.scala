package pl.edu.pw.ee.lzw

import javax.print.DocFlavor.BYTE_ARRAY

import ByteArrayComparatorTest._
import org.junit.{Before, Test}
import org.junit.Assert._

/**
  * Created by clutroth on 16.11.16.
  */
class ByteArrayComparatorTest {

  var comp: ByteArrayComparator = _

  @Before def setUp: Unit = {
    comp = new ByteArrayComparator
  }

  @Test def testOneSmallOneBig = assertTrue(comp.compare(oneSmall, oneBig) < 0)

  @Test def testOneBigOneSmall = assertTrue(comp.compare(oneBig, oneSmall) > 0)

  @Test def testOneSmallOneSmall = assertTrue(comp.compare(oneSmall, oneSmall) == 0)

  @Test def testOneSmallTwoSmall = assertTrue(comp.compare(oneSmall, twoSmall) < 0)

  @Test def testTwoSmallOneBig = assertTrue(comp.compare(twoSmall, oneBig) < 0)

  @Test def testTwoBigOneSmall = assertTrue(comp.compare(twoBig, oneSmall) > 0)

  @Test def testEmptyOneSmall = assertTrue(comp.compare(empty, oneSmall) < 0)

  @Test def testOneSmallEmpty = assertTrue(comp.compare(oneSmall, empty) > 0)

  @Test def testEmptyEmpty = assertTrue(comp.compare(empty, empty) == 0)

}
object ByteArrayComparatorTest {
  val oneSmall = Array[Byte](0)
  val oneBig = Array[Byte](1)
  val twoSmall = Array[Byte](0, 0)
  val twoBig = Array[Byte](0, 1)
  val empty = Array[Byte]()
}

package pl.edu.pw.ee.lzw

import org.junit.{Before, Test}
import org.junit.Assert._

/**
  * Created by clutroth on 21.11.16.
  */
class CompressDictionaryTest {

  var compression: BufferedDictionary[Byte, Int] = ???

  @Before def setUp = {
    compression = _
  }

  @Test def initDictionaryTest = {
    def compress(list: List[Byte]) =
      list.map(compression.put).map(_.get)
    assertEquals(List[Integer](0, 1, 256), compress(List[Byte](0, 1, 0, 1)))
  }

}

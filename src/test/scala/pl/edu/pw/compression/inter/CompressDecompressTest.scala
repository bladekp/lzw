package pl.edu.pw.compression.inter

import org.scalatest.{FlatSpec, Matchers}
import pl.edu.pw.compression.{LZWCompression, LZWDecompression}

/**
  * Created by clutroth on 10.12.16.
  */
class CompressDecompressTest extends FlatSpec with Matchers {
  "LZWCompression and LZWDecompression" should "be symmetric" in {
    val data: Array[Byte] = "abcdabcdbdbabcd".getBytes()
    def compress(comp: LZWCompression, data: Array[Byte], output: List[Int]): List[Int] = {
      if (data.isEmpty)
        return output :+ comp.lastValue.get
      val newComp: LZWCompression = comp.push(data.head)
      if (newComp.value.isDefined) {
        compress(newComp, data.tail, output :+ newComp.value.get)
      } else
        compress(newComp, data.tail, output)
    }
    val dictionary: Array[Byte] = "abcd".getBytes
    val comp: LZWCompression = LZWCompressionImpl.begin(dictionary, data.head)
    val compressed: List[Int] = compress(comp, data.tail, List())
    def decompress(decomp: LZWDecompression, data: List[Int], output: List[Byte]): List[Byte] = {
      if (data.isEmpty) {
        return output
      }
      val newDecomp = decomp.put(data.head)
      decompress(newDecomp, data.tail, output ++ newDecomp.value)
    }
    val decomp = LZWDecompressionImpl.begin(dictionary, compressed.head)
    val decompressed = decompress(decomp, compressed.tail, decomp.value)
    assert(decompressed.toArray ==  data)

  }
}

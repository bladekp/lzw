package pl.edu.pw.compression

import java.io.{InputStream, OutputStream}

import pl.edu.pw.compression.inter.LZWCompressionImpl

import scala.io.Source


/**
  * Created by clutroth on 30.11.16.
  */
object LZW2 {
  def decompress(os: OutputStream, dictionary: Array[Byte]): Unit = {

  }

  def compress(s: Source, dictionary: Array[Byte]): Array[Int] = {
    if (!s.hasNext)
      throw new IllegalStateException("Empty source")
    val b = s.next.toString.getBytes
    val comp =
      LZWCompressionImpl.begin(dictionary, b.head)
    if (b.length == 1)
      Compression.compress(comp, s, List.empty).toArray
    else {
      val t = Compression.readBuffer(comp, b.tail, List.empty)
      Compression.compress(t._1, s, t._2).toArray
    }
  }

  def compress(is: InputStream, dictionary: Array[Byte]): Array[Int] =
    compress(Source.fromInputStream(is), dictionary)

  def compress(s: String, dictionary: Array[Byte]): Array[Int] =
    compress(Source.fromString(s), dictionary)

  object Decompression {
    val BUF_SIZE = 1<<10
    def decompress(dec:LZWDecompression,os:OutputStream,)
  }

  object Compression {
    def compress(comp: LZWCompression, s: Source, res: List[Int]): List[Int] = {
      if (!s.hasNext)
        res :+ comp.lastValue.get
      else {
        val buf = s.next().toString.getBytes
        val buffered = readBuffer(comp, buf, res)
        compress(buffered._1, s, buffered._2)
      }

    }

    def readBuffer(comp: LZWCompression, buf: Array[Byte], res: List[Int]): (LZWCompression, List[Int]) = {
      if (buf.isEmpty)
        (comp, res)
      else {
        val nextComp = comp.push(buf.head)
        val nextRes =
          if (nextComp.value.isDefined)
            res :+ nextComp.value.get
          else
            res
        readBuffer(nextComp, buf.tail, nextRes)
      }
    }
  }

}

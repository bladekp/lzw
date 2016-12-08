package pl.edu.pw.compression.inter

import java.io.{InputStream, OutputStream}

import pl.edu.pw.compression.LZWCompression


/**
  * Created by clutroth on 01.12.16.
  */
object LZW {
  val BUF_SIZE = 1 << 10

  def decompress(from: InputStream, to: OutputStream, dictionary: Array[Byte]) =
    ???

  def compress(from: InputStream, to: OutputStream, dictionary: Array[Byte]) = {
    def firstCharacter = {
      val b = Array[Byte](1)
      from.read(b)
      b.head
    }
    val comp = LZWCompressionImpl.begin(dictionary, firstCharacter)
    val buf = new Array[Byte](BUF_SIZE)
    def readBuffer(buf:Array[Byte]) ={
if(from.)
    }
  }

}

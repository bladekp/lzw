package pl.edu.pw.compression

/**
  * Created by clutroth on 30.11.16.
  */
trait LZWDecompression {
  def put(k:Int):LZWDecompression
  def value: List[Byte]

}

package pl.edu.pw.compression

/**
  * Created by clutroth on 30.11.16.
  */
trait LZWCompression {
  def push(b: Byte): LZWCompression

  def value: Option[Int]

  def lastValue: Option[Int]

}

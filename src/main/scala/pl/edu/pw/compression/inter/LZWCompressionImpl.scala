package pl.edu.pw.compression.inter

import pl.edu.pw.compression.LZWCompression


/**
  * Created by clutroth on 30.11.16.
  */
class LZWCompressionImpl(val map: Map[String, Int],
                         val c: List[Byte],
                         _value: Option[Int]) extends LZWCompression {
  override def push(b: Byte): LZWCompression = {
    val pushed = LZWCompressionImpl.push(b, c, map)
    new LZWCompressionImpl(pushed._1, pushed._2, pushed._3)
  }

  override def value: Option[Int] = _value

  override def lastValue: Option[Int] = LZWCompressionImpl.lastValue(map, c)
}

object LZWCompressionImpl {
  def begin(characters: Array[Byte], firstCharacter: Byte) :LZWCompression= {
    val init = initialize(characters, firstCharacter)
    new LZWCompressionImpl(init._1, init._2, None)
  }

  def initialize(characters: Array[Byte], firstCharacter: Byte): (Map[String, Int], List[Byte]) = {
    if (characters.toSet.size != characters.length)
      throw new IllegalArgumentException("characters has duplicated elements")
    else if (!characters.contains(firstCharacter))
      throw new IllegalArgumentException("firstCharacter is not member of characters")
    else
      (characters.map(_.toChar.toString).zip(Stream from 1).toMap,
        List(firstCharacter))
  }

  def toString(cs: List[Byte]): String =
    new String(cs.toArray)

  def push(s: Byte, c: List[Byte], map: Map[String, Int]): (Map[String, Int], List[Byte], Option[Int]) = {
    if (!map.contains(toString(c)))
      throw new IllegalArgumentException(s"${toString(c)} is not present in dictionary")
    if (!map.contains(s.toChar.toString))
      throw new IllegalArgumentException(s"${s.toChar} is not present in dictionary")
    if (c.isEmpty)
      throw new IllegalArgumentException("c is empty")
    val cs = c :+ s
    if (map.contains(toString(cs)))
      (map, cs, None)
    else
      (map + (toString(cs) -> (map.size + 1)),
        List(s),
        map get toString(c))

  }

  def lastValue(map: Map[String, Int], c: List[Byte]) = {
    map get toString(c)
  }

}

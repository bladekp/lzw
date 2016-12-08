package pl.edu.pw.compression.inter

import pl.edu.pw.compression.LZWDecompression

/**
  * Created by clutroth on 30.11.16.
  */
class LZWDecompressionImpl extends LZWDecompression {
  override def put(k: Int): LZWDecompression = ???

  override def value: List[Byte] = ???
}

object LZWDecompressionImpl {
  def initialize(dic: List[String], pk: Int): (Map[Int, String], Int, String) = {
    val map =
      dic.zip(Stream from 1).map(e => (e._2, e._1)).toMap
    if (dic.isEmpty)
      throw new IllegalArgumentException("dic is empty")
    else if (dic.toSet.size != dic.size)
      throw new IllegalArgumentException("has double values")
    else if (!map.contains(pk))
      throw new IllegalArgumentException("pk doesn't exists in dic")
    else
      (map, pk, map(pk))
  }

  def decompress(dic: Map[Int, String], pk: Int, k: Int): (Map[Int, String], Int, String) = {
    val pc = dic(pk)
    def appendFirstByte(s1: String, s2: String) =
      new String(s1.getBytes :+ s1.getBytes.head)
    def add[T](dic: Map[Int, T], word: T) =
      dic + ((dic.size + 1) -> word)
    if (dic.contains(k))
      (add(dic, appendFirstByte(pc, dic(k))), k, dic(k))
    else {
      val res = appendFirstByte(pc, pc)
      (add(dic, res), k, res)
    }
  }
}

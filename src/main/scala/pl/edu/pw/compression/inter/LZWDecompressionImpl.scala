package pl.edu.pw.compression.inter

import pl.edu.pw.compression.LZWDecompression
import LZWDecompressionImpl._

/**
  * Implementacja dekompresji też jest nie mutowalna :D
  * Created by clutroth on 30.11.16.
  */
class LZWDecompressionImpl(val dic: Map[Int, String],
                           val pk: Int,
                           _value: List[Byte]) extends LZWDecompression {
  override def put(k: Int): LZWDecompression = {
    create(decompress(dic, pk, k))
  }

  override def value: List[Byte] =
    _value
}

object LZWDecompressionImpl {
  /**
    * Rozpoczyna proces dekompresji
    * @param characters słownik
    * @param pk pierwszy klucz do zdekodowania
    * @return zainicjalizowany obiekt dekompresujący
    */
  def begin(characters: Array[Byte], pk: Int): LZWDecompression = {
    create(initialize(characters.map(_.toChar).map(_.toString).toList, pk))
  }

  def create(beg: (Map[Int, String], Int, String)) =
    new LZWDecompressionImpl(beg._1, beg._2, beg._3.getBytes.toList)

  def dic2Map(dic: List[String]) =
    dic.zip(Stream from 1).map(e => (e._2, e._1)).toMap

  def initialize(dic: List[String], pk: Int): (Map[Int, String], Int, String) = {
    val map = dic2Map(dic)
    if (dic.isEmpty)
      throw new IllegalArgumentException("dic is empty")
    else if (dic.toSet.size != dic.size)
      throw new IllegalArgumentException("has duplicated values")
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

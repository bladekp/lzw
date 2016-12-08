package pl.edu.pw.compression.inter

import org.scalatest.{FlatSpec, Matchers}
import LZWDecompressionImpl._

/**
  * Created by clutroth on 30.11.16.
  */
class LZWDecompressionImplTest extends FlatSpec with Matchers {
  def stringToList(s: String) =
    s.toCharArray.map(_.toString).toList

  "initialize" should "create dictionary" in {
    val actual = initialize(stringToList("ab"), 1)
    assert(Map(1 -> "a", 2 -> "b") === actual._1)
    assert(1 === actual._2)
    assert("a" === actual._3)
  }
  it should "fail on empty list" in {
    intercept[IllegalArgumentException] {
      initialize(List(), 1)
    }
  }
  it should "fail on duplicated list values" in {
    intercept[IllegalArgumentException] {
      initialize(stringToList("aba"), 1)
    }
  }
  it should "fail on pk non existing in dictionary" in {
    intercept[IllegalArgumentException] {
      initialize(stringToList("ab"), 100)
    }
  }
  "decompress" should "return known word" in {
    val actual = decompress(Map(1 -> "abc"), 1, 1)
    assert(Map(1 -> "abc",2->"abca") === actual._1)
    assert(1 === actual._2)
    assert("abc" === actual._3)
  }
  it should "return unknown word" in {
    val actual = decompress(Map(1 -> "a"), 1, 2)
    assert(Map(1 -> "a",2->"aa") === actual._1)
    assert(2 === actual._2)
    assert("aa" === actual._3)

  }
}

package pl.edu.pw.compression.inter

import org.scalatest.{FlatSpec, Matchers}
import pl.edu.pw.compression.inter.LZWCompressionImpl._
import pl.edu.pw.compression.LZWCompression


/**
  * Created by clutroth on 30.11.16.
  */
class LZWCompressionImplTest extends FlatSpec with Matchers {
  "initialization" should "add each character" in {
    val res = initialize("abcd_".getBytes, 'a')
    val map: Map[String, Int] = res._1
    assert(map === Map("a" -> 1,
      "b" -> 2,
      "c" -> 3,
      "d" -> 4,
      "_" -> 5
    ))
    assert(res._2.size === 1)
    assert(res._2.head === 'a')
  }
  it should "fail if firstCharacter is not from dictionary" in {
    intercept[IllegalArgumentException] {
      initialize("ab".getBytes, 'c')
    }
  }
  it should "fail on repetition in dictionary definition" in {
    intercept[IllegalArgumentException] {
      initialize("aba".getBytes, 'a')
    }
  }
  "push" should "if c+s present in map, then c:=c+s" in {
    val actual = push('a', List('a'.toByte), Map("a" -> 1, "aa" -> 2))
    assert(actual._1 === Map("a" -> 1, "aa" -> 2))
    assert(actual._2 === "aa".getBytes.toList)
    assert(actual._3.isEmpty)
  }
  it should "if c+s is new, code for c on output, add c+s to map, and c:=s" in {
    val actual = push('a', List('b'.toByte), Map("a" -> 1,
      "b" -> 2))
    assert(actual._1 === Map("a" -> 1, "b" -> 2, "ba" -> 3))
    assert(actual._2 === "a".getBytes.toList)
    assert(actual._3.isDefined)
    assert(actual._3.get === 2)

  }
  it should "throw exception if c is not present in map" in {
    intercept[IllegalArgumentException] {
      push('c', "a".getBytes.toList, Map("a" -> 1))
    }
  }
  it should "throw exception if s is not present in map" in {
    intercept[IllegalArgumentException] {
      push('a', "c".getBytes.toList, Map("a" -> 1))
    }
  }
  it should "throw exception if c is empty" in {
    intercept[IllegalArgumentException] {
      push('a', List(), Map("a" -> 1))
    }
  }
  "lastValue" should "return value of c" in {
    val last = lastValue(Map("abc" -> 4), "abc".getBytes.toList)
    assert(last === Some(4))
  }
  "wiki example" should "just pass" in {
    def assertPush(s: Byte, n: Option[Int])(sc: LZWCompression): LZWCompression = {
      val newSc = sc.push(s)
      assert(n === newSc.value)
      newSc
    }
    Seq(begin("abcd_".getBytes, 'a'))
      .map(assertPush('b', Some(1)))
      .map(assertPush('c', Some(2)))
      .map(assertPush('c', Some(3)))
      .map(assertPush('d', Some(3)))
      .map(assertPush('_', Some(4)))
      .map(assertPush('a', Some(5)))
      .map(assertPush('b', None))
      .map(assertPush('c', Some(6)))
      .map(assertPush('c', None))
      .map(assertPush('d', Some(8)))
      .map(assertPush('_', None))
      .map(assertPush('a', Some(10)))
      .map(assertPush('c', Some(1)))
      .map(assertPush('d', None))
      .map(assertPush('_', Some(9)))
      .map(assertPush('a', None))
      .map(assertPush('c', Some(11)))
      .map(assertPush('d', None))
      .map(assertPush('_', None))
      .map(assertPush('a', Some(16)))
      .map(assertPush('c', None))
      .map(assertPush('d', Some(15)))
      .map(_.push('_'))
      .foreach(l => assert(Some(10) === l.lastValue))

  }

}

package pl.edu.pw.ee.lzw

/**
  * Created by clutroth on 21.11.16.
  */
trait BufferedDictionary[K, V] {
  def put(k: K): Option[V]
}

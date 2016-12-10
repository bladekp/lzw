package pl.edu.pw.compression

/**
  * Interfejs służy do przeprowadzania kompresji algorytmem LZW.
  * Created by clutroth on 30.11.16.
  */
trait LZWCompression {
  /**
    * wrzuca bajt do kompresora
    *
    * @param b bajt do skompresowania
    * @return nowy obiekt kompresora
    */
  def push(b: Byte): LZWCompression

  /**
    * Wartość reprezentująca skompresowane dane. Jeśli istnieje, zapisać na wyjście
    *
    * @return
    */
  def value: Option[Int]

  /**
    * Ostatnia znana wartość. Zapisanie tej wartości jest ostatnim krokiem algorytmu.
    *
    * @return
    */
  def lastValue: Option[Int]

}

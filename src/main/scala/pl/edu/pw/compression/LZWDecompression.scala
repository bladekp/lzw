package pl.edu.pw.compression

/**
  * Interfejs służy do przeprowadzania dekompresji algorytmem LZW.
  * Created by clutroth on 30.11.16.
  */
trait LZWDecompression {
  /**
    * Wrzuca klucz do dekompresora
    * @param k klucz reprezentujący skompresowane dane
    * @return obiekt dekompesora
    */
  def put(k:Int):LZWDecompression

  /**
    * Dane zdekodowane na podstawie ostatniego klucza.
    * @return
    */
  def value: List[Byte]

}

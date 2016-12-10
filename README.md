# Interfejs programisty

Klasa *pl.edu.pw.compression.LZW* zawiera dwie metody:

* compress
* decompress

Obydwie przyjmują strumień wejścia, strumień wyjścia,
oraz tablicę będącą słownikiem znanych wyrazów.

Metoda *compress* czyta bajty ze strumienia wejściowego
i zapisuje skompresowany plik na strumień wyjściowy.

Metoda *decompress* działa analogicznie.

Klasa *LZWCompressionImpl* implementuje interfejs
*LZWCompression*. Pozwala on zakodować strumień bajtów
do listy liczb, bedących skompresowanymi danymi.
Nie odpowiada ona za komunikację z wejściem.
Zawiera wyłącznie logikę algorytmu kompresji.

Klasa *LZWDecmomperssionImpl* pełni analogiczną rolę.

W celu zainicjalizowania kompresji, należy wywołać
metodę *begin*. Przyjmuje ona tablicę bajtów, jako
słownik znanych słów, oraz, dla komprsji pierwszy bajt,
dla dekompresji pierwszy klucz z zakodowanych danych.

Dodanie kolejnego klucza, lub bajtu do zakodowania
skutkuje stworzeniem nowego obiektu. Ma on w sobie
słownik i rezultat ostaniego korku kompresji/dekompresji

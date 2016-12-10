# Program demonstrujący zastosowanie algorytmu LZW do kompresji plików tekstowych
Autorzy: Błądek Piotr bladekp@ee.pw.edu.pl, Decker Wojciech deckerw@ee.pw.edu.pl

## Technologia
Program zostanie napisany wjęzyku Java SE 8, bez użycia dodatkowych bibliotek. Będzie to aplikacja konsolowa.

## Działanie programu
Program będzie uruchamiany z konsoli w następujący sposób:

`java -jar lzw.jar OPCJA [ŹRÓDŁO] [CEL]`

Gdzie ŻRÓDŁO to plik wejściowy, CEL plik wyjściowy, a OPCJA to -k w przypadku kompresji pliku żródłowego do decelowego lub -d w przypadku dekompresji pliku żródłowego do decelowego. Wszystkie parametry są wymagane. Można też użyć programu z opcją -h w celu pokazania pomocy. Na koniec działania program wypisze ile miejsca udało się zaoszczędzić dzięki kompresji.


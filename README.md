# regerege_rekka

Helsingin yliopiston Aineopintojen harjoitustyö: Tietorakenteet ja algoritmit

### Dokumentaatio
* [Määrittelydokumentaatio](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/definition.md)
* [Toteutusdokumentaatio](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/implementation.md)
* [Testausdokumentaatio](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/testing.md)
* [Käyttöohje](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/manual.md)

# Asennusohje

Kloonaa projekti koneellesi

```
git clone https://github.com/Hiipivahalko/regerege_rekka.git
```
Meno projektion juuresta polkuun `./regerege_rekka/` ja suorita komento


```gradle build```

Ohjelma toimii tämän jälkeen seuraavasti

```
java -jar ./build/libs/regerege_rekka.jar <regex_lauseke> <tiedosto1>
```

## Käyttöohje

* Ohjelmalle täytyy antaa kaksi parametria aina kun sitä käyttää. Ensimmäinen näistä on säännöllinen lauseke, jonka jälkeen täytyy laittaa vähintää yksitiedosto mitä tutkia, mutta voi myös laittaa niin monta haluaa
* Säännöllinen lauseke tukee seuraavia perinteisiä toimintoja, kuten: 
 
* whildcard (*), edeltävää merkkiä nolla tai äärettömän monta kertaa
* piste (.), eli tällöin säännöllinen lauseke ymmärtää pisteen minätahansa merkkinä
* putki (|), tällä merkillä voit tehdä kaksi eri vaihtoehtoa haulle
* hakasulku ([]), tätä voi käyttää kirjoittamaan lyhyemmin putki merkinnän, jos haluat etsiä haarautumista yhdenkirjaimen kohdalla (esim. [A-D] -> (A|B|C|D)
* suluilla tekstiä pystyy ryhmittelemään ja käyttämään hyväksi yllä mainittuja operaatioita esim. (ab)|(ac) -> "ab tai ac" tai (ab)* -> ab 0-n kertaa
* Ja jos haluat käyttää seurravia merkkejä normaalina kirjaimina, niin niiden eteen tulee laittaa escape merkki `\` -> `(` `)` `[` `]` `\` `|` `*` `.`

* säännöllistälauseketta ei ohjelman suoritusaikana valitoida, joka tarkoittaa sitä että käyttäjän on oltava huolellinen haka- ja normallien sulkujen ka
nssa, sillä ohjelma saattaa kaatua muuten.

## Ohjelman kokeilu

* projektin mukana on myös yksi pieni testitiedosto, jolla voi testailla työkalua. Se sijaitsee projektin polussa ./regerege_rekka/src/main/resource/tes
tfile.txt
* Sitä voi esim testailla esimerkiksi seuraavalla komennolla
```
java -jar ./build/libs/regerege_rekka.jar "Tira" ./regerege_rekka/src/main/resource/testfile.txt
```


### Viikkoraportit

[Viikko 1](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/weeklyRaports/week1.md),
[Viikko 2](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/weeklyRaports/week2.md),
[Viikko 3](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/weeklyRaports/week3.md),
[Viikko 4](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/weeklyRaports/week4.md),
[Viikko 5](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/weeklyRaports/week5.md)
[Viikko 6](https://github.com/Hiipivahalko/regerege_rekka/blob/master/documentation/weeklyRaports/week6.md)


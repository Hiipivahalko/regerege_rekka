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

⋅⋅⋅whildcard (*), edeltävää merkkiä nolla tai äärettömän monta kertaa
⋅⋅⋅piste (.), eli tällöin säännöllinen lauseke ymmärtää pisteen minätahansa merkkinä
⋅⋅⋅putki (|), tällä merkillä voit tehdä kaksi eri vaihtoehtoa haulle
⋅⋅⋅hakasulku ([]), tätä voi käyttää kirjoittamaan lyhyemmin putki merkinnän, jos haluat etsiä haarautumista yhdenkirjaimen kohdalla (esim. [A-D] -> (A|B|C|D)
⋅⋅⋅Ja jos haluat käyttää seurravia merkkejä normaalina kirjaimina, niin niiden eteen tulee laittaa escape merkki `\` -> `(` `)` `[` `]` `\` `|` `*` `.`

* säännöllistälauseketta ei ohjelman suoritusaikana valitoida, joka tarkoittaa sitä että käyttäjän on oltava huolellinen haka- ja normallien sulkujen ka
nssa, sillä ohjelma saattaa kaatua muuten.

## Ohjelman kokeilu

* projektin mukana on myös yksi pieni testitiedosto, jolla voi testailla työkalua. Se sijaitsee projektin polussa ./regerege_rekka/src/main/resource/tes
tfile.txt
* Sitä voi esim testailla esimerkiksi seuraavalla komennolla
```
java -jar ./build/libs/regerege_rekka.jar "Tira" ./regerege_rekka/src/main/resource/testfile.txt
```


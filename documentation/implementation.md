# Toteutusdokumentti

### Ohjelman rakennetta
* Ohjelma ensiksi purkaa annetun säännöllisen lausekkeen ja rakentaa siitä sen muokaisen NFA-verkon. NFA-verkko saattaa sisältää kaaria joita pitkin pääsee liikkumaan tyhjillä merkeillä. NFA-verkon solmulla voi olla samalle symbolilla monta eri kaarta. Verkolla on yksi tulosolmu, mistä lähdetään liikkeelle jos halutaan tutkia verkossa liikkumista. Verkolla on myös yksi ns. "maalisolmu" johon päästyäsi olet kulkenut onnistuneesti verkossa, toisin sanoen verkko tunnistaa annetun osamerkkijonon. Verkon rakentamisessa noudatetaan [Thompsonin algorimita](https://en.wikipedia.org/wiki/Thompson%27s_construction). Säännöllinen lauseke ensin esikäsitellään  hieman, esim poistetaan hakasulkujen käyttö union merkeiksi (“|”) ja lisätään sopiviin kohtiin yhdistämistä tarkoittava merkki kahden symbolin välille. Sitten purkaminen etenee käymällä säännöllisen lausekkeen merkki merkiltä uudelleen läpi ja totetuttamalla annetut toiminnallisuudet. Toiminnallisuuksia muokata/rakentaa verkkoa on kolme, yhdistäminen, tähti ja hajaannus. Jokainen säännöllisen lausekkeen kirjaimelle luodaan kaari solmusta a, solmuun b.  Yhdistämisesoperaatiossa kähden osaverkon/solmun välille luodaan uusi solmu johon epsilon siirrot molemmista suunnista (esim. Jos a b yhdistetään, niin tulisi a -> “tyhjäUusi” -> b). Tähti operaatiossa luodaan osaverkon ensimmäiselle solmulle A ns. Looppi, eli tälle luodaan uusi vanhempi solmu S, josta pääsee ensimmäiseen solmuun epsilon siirrolla. Tälle uudelle solmulle S luodaan vielä toinen solmu K johon päästään sekä S:stä ja A:sta epsilon siirroilla. Haara operaatiossa NFA-verkkoon luodaan siis haarakohta jossa tie maali solmulle haarautuu kahteen reittiin, näille reiteille luodaan uudet yhteiset alku- ja päätesolmut epsilon siirroilla. 

* Kun NFA-verkko on saatu luotua, niin rakennamme siitä vielä DFA-verkon jotta merkkijonon/kielen tunnistaminen onnistuu helpommin ja jokaisella solmulla on maksimissaan yksi liikkumissuunta seuraavaan solmuun symbolilla x. DFA-verkko saadaan rakennettua NFA-verkosta [Powerset algoritmin](https://en.wikipedia.org/wiki/Powerset_construction) avulla.
Algoritmissa perusideana, muodostaa uniikkeja solmujoukkoja NFA-verkosta, jolloin voimme poistaa mahdolliset epsilon ja moni haaraiset siirtymät tietyillä symboleilla. Aluksi aloitetaan NFA-verkon lähtösolmusta ja nidotaan mahdolliset solmut joihin päästään epsilon siitymällä, tästä syntyy DFA-verkon uusi lähtösolmu. Yksi tälläinen “solmujoukko” on DFA-verkon yksi solmu. Tämän jälkeen algoritmi tarkistaa pystyykö se liikkumaan mihin muihin NFA-solmuihin tietyllä symbolilla omasta solmujoukostaan, kun jokin NFA-verkon solmuun löydetään yhteys se lisätään uuteen mahdolliseen solmujoukkoon ja lopulta jos joukko on uniikki niin se on DFA-verkon uusi solmu ja sille tarkistetaan mahdolliset omat liikkumiset NFA-verkossa. Tätä jatketaan, niin kauan kunnes ei löydy uniikkeja solmujoukkoja DFA-verkolla on myös yksi lähtösolmu, mutta sillä voi olla enemmän kuin yksi maalisolmu.

* Kun DFA-verkko on valmis, niin voimme lähteä etsimään totetuttaako annetun tiedoston rivit rakentamaamme DFA-verkkoa. Eli käytännössä yritämme kulkea DFA-verkkon kaaria pitkin rivin symboli kerrallaan ja jos päädymme symbolilla x solmuun joka on maalisolmu, niin rivi tunnistetaan DFA-verkon puolesta ja säännöllisyys on löydetty. Tällöin myös rivi tulostetaan näkyviin konsoliin käyttäjälle.

### O-Analyysit

* Ohjelman O-analyysin aikavaativuuksiksi saadaan O(N+M), missä N on säännöllisenlausekkeen pituus ja M on annettujen tiedostojen merkkien määrä.
* Isompia tiedostoja käyttäessä N pituuden vaikutus hiekkenee, sillä sen suhde on paljon pienempi verrattuna M. Yleisesti säännölliset lausekkeet ovat tiiviitä ja lyhyitä, mutta ovat myös yleisesti monimutkaisia mikä saattaa kasvattaa hiukan N:n arvoa, kun monimutkainen lauseke puretaan kolmeen operaatiomerkkeihin.
* Tilavaativuudeltaan ohjelma vaatii käytännössä O(1), koska ei ohjelma ei lataa tiedostoja muistiin ohjelman pyöriessä

### Puutteet
Esimerkkejä muutamista parannus kohdista
* Monia säännöllisten lausekkeiden rakenteita ja toteuttamatta, kuten esimerkiksi “+”-symbolin toiminnallisuus. Moni toiminnallisuus kuitenkin pohjautuu/soveltuu kolmeen jo totetutettuun toiminnallisuuteen, eli: concat, start, union
* Löydetylle osamerkkijonolle olisi voinut lisätä värit jotta käyttän helmpompi huomata mikä osa merkkijonosta oli se mikä haluttiin
* Myös toiminnon jossa ei ole väliä onko kirjain iso- (A) vai pienikirjain (a) olis voinut toteuttaa.



## Sanapuuro

### Yleiskuva

Tämä projekti on jatkoa Sanapuuro-pelille, jonka tein JavaLabraa varten. Yksinpeliä ei enää ole vaan se on korvattu kaksinpelillä. Peliä pelataan lokaalisti tietokonetta vastaan. Pelaaja ohjaa "Hessua" ja tietokonevastustaja "Mikkiä". Pelaajan on muodostettava englanninkielinen sana kirjaimistaan.

Pelin ajettava jar löytyy [täältä](http://www.cs.helsinki.fi/u/skaipio/jars/sanapuuro.jar).



__Pelin ajaminen puhtaasti terminaalissa:__
- java -jar sanapuuro.jar



__Pelin Swing-version ajaminen (suositeltu):__
- java -jar sanapuuro.jar -w

### Käyttöohjeet

__Swing-versio:__
- Hiirellä valitaan paikka josta aloitetaan sanan syöttäminen.
- Kun paikka on valittu, on valittava vielä syöttösuunta, joko hiirellä tai 'wasd'-näppäimillä.
- Kun suunta on valittu voidaan syöttää kirjaimia. Kirjaimia, jotka eivät löydy omista kirjaimista ei pysty syöttämään.
- Kirjaimien syöttämisen voi myös aloittaa solusta, jossa on jo kirjain.
- Kirjaimen, joka on korostetun solun kohdalla, voi valita syötteeseen välilyönnillä.
- Kirjaimen syöttämisen tai valitsemisen voi peruuttaa 'Backspace'-näppäimellä, jolla myös pääsee pois syöttötilasta.
- Sana annetaan arvioitavaksi 'Submit'-nappulaa tai 'Enter'-näppäintä painamalla.


__Terminaali-versio__
- Kursoria voi liikutella 'w', 'a', 's' ja 'd' komentojen avulla.
- Kun halutaan syöttää sana kursorin osoittamasta kohdasta alkaen, annetaan komento 'q'. Peli pyytää sen jälkeen suunnan ja syötettävät kirjaimet.
- Pelaaja voi skipata vuoronsa syöttämällä tyhjän merkkijonon.
- Pelaajan pisteet sekä käytettävissä olevat kirjaimet ovat näkyvissä konsolitulosteessa.
- Poistumiseen pelistä ei ole erillistä komentoa, vaan sen voi pysäyttää terminaalissa komennolla Ctrl+C.

* * *

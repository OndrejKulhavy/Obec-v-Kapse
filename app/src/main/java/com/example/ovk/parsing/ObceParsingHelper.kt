package com.example.ovk.parsing

class ObceParsingHelper(val results: Results){

    class Results(val bindings : List<Obec>)

    class Obec(val nazev : Nazev, val popis: Popis, val url : UrlAdresa, val ahoj : String)

    class Nazev(val value : String)

    class Popis(val value : String)

    class UrlAdresa(val value : String)

}


package com.example.ovk.parsing

class ObceParsingHelper(val results: Results){

    class Results(val bindings : List<Obec>)

    class Obec(val nazev : Nazev, val popis: Popis, val url : UrlAdresa, val misto : Misto)

    class Misto(val value: String)

    class Nazev(val value : String)

    class Popis(val value : String)

    class UrlAdresa(val value : String)

}


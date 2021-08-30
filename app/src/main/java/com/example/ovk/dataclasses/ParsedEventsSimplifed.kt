package com.example.ovk.dataclasses

data class ParsedEventsSimplifed(val list: ArrayList<ZjednodusenaUdalost>)

data class ZjednodusenaUdalost(val obsahuje : Obsahuje, val data : UlozenaData)

data class UlozenaData(
        val iri : String,
        val nazev : String,
        val popis : String,
        val dlouhy_popis : String,
        val vhodne_pro_deti : Boolean,
        val vhodne_pro_zvriata: Boolean
)

data class Obsahuje(
        val maIRI : Boolean,
        val maNAZEV : Boolean,
        val maDLOUHY_POPIS : Boolean,
        val maPOPIS : Boolean,
        val maVHODNE_PRO_DETI : Boolean,
        val maVHODNE_PRO_ZVIRITA : Boolean
        //,val maKONTAKTY : Boolean
)

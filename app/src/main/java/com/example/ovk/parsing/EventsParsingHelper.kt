package com.example.ovk.parsing

import com.google.gson.annotations.SerializedName

class EventsParsingHelper (val udalosti : List<Udalost>
    ){

    class Udalost(
        @SerializedName("iri")
        val iri : String,
        @SerializedName("název")
        val nazev : NazevEvents,
        @SerializedName("dlouhý_popis")
        val dlouhy_popis : DlouhyPopisEvents,
        @SerializedName("popis")
        val popis : PopisEvents,
        @SerializedName("vhodné_pro_děti")
        val vhodne_pro_deti : Boolean,
        @SerializedName("vhodné_pro_zvířata")
        val vhodne_pro_zvirata : Boolean,
        /*@SerializedName("kontakt")
        val kontakt : KontaktEvents*/
        )

    class PopisEvents(val cs: String)

    class NazevEvents(val cs: String)

    class KontaktEvents(
        val mobil : String,
        val email : String,
        val facebook : String,
        val twitter : String,
        val instagram : String,
        val telefon : String,
        val url : String,
        val web : String
        )

    class DlouhyPopisEvents (val cs : String)
}

/*    @SerializedName("pořadatel")
    val poradatel : PoradatelEvents,
    @SerializedName("hlavní_sponzor")
    val hlavni_sponzor: HlavniSponzorEvents,
    @SerializedName("sponzor")
    val sponzor: SponzorEvents,
    @SerializedName("mediální_partner")
    val medialni_sponzor : MedialniSponzorEvents,
    @SerializedName("umístění")
    val umisteni : UmisteniEvents,
    @SerializedName("typ_události")
    val typ_udalosti : TypUdalostiEvents,
    @SerializedName("téma")
    val tema : TemaEvents,
    @SerializedName("bezbariérovost")
    val bezbarierovost : BezbarierovostEvents,
    @SerializedName("doba_trvání")
    val doba_trvani : DobaTrvaniEvents,
    @SerializedName("vstupné")
    val vstupne : VstupneEvents,*/
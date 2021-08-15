package com.wse.project.networkdb

import com.google.gson.annotations.SerializedName

data class Datum (
    @SerializedName("_id")
     var id: String? = null,

    @SerializedName("name")
     var name: String? = null,

    @SerializedName("trips")

     var trips: Int? = null,

    @SerializedName("airline")
     var airline: List<Airline>,

    @SerializedName("__v")

    private var v: Int? = null




)
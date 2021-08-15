package com.wse.project.networkdb
import com.google.gson.annotations.SerializedName

data class PassengersModel(
    @SerializedName("totalPassengers")
    var totalPassengers: Int? = null,
    @SerializedName("totalPages")
    var totalPages: Int? = null,
    @SerializedName("data")
    var data: List<Datum>


)
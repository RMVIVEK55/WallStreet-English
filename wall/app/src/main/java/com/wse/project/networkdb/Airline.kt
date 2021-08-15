package com.wse.project.networkdb

import com.google.gson.annotations.SerializedName

data class Airline (
    @SerializedName("id")

     var id: Int? = null,

    @SerializedName("name")

     var name: String? = null,

    @SerializedName("country")

     var country: String? = null,

    @SerializedName("logo")

     var logo: String? = null,

    @SerializedName("slogan")

     var slogan: String? = null,

    @SerializedName("head_quaters")

     var headQuaters: String? = null,

    @SerializedName("website")

     var website: String? = null,

    @SerializedName("established")

     var established: String? = null



)
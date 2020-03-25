package com.example.xreader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Book (
    @SerializedName("id") val id : Int?,
    @SerializedName("chapters") var chapters : List<Chapter>?,
    @SerializedName("title") val title : String?
):Serializable{
    constructor() : this(null,null,null) {

    }
}
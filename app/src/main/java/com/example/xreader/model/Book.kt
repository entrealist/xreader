package com.example.xreader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Book  (
    @SerializedName("id") val id : String?,
    @SerializedName("chapters") var chapters : List<Chapter>?,
    @SerializedName("title") val title : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("image") val image : String?
):Serializable{
    constructor() : this(null,null,null,null,null) {

    }


}
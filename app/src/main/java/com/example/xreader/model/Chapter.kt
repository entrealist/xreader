package com.example.xreader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Chapter (
    @SerializedName("id") val id : String?,
    @SerializedName("content") val content : String?,
    @SerializedName("title") val title : String?
):Serializable{
    constructor() : this(null,null,null) {

    }
}
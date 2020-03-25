package com.example.xreader.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Chapter (
    @SerializedName("id") val id : Int,
    @SerializedName("content") val content : String?,
    @SerializedName("title") val title : String?
):Serializable{
    constructor() : this(0,null,null) {

    }
}
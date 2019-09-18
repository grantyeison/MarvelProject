package com.prometheus.marvelproject.model

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject


abstract class ServiceResult{
    @SerializedName("copyright") val copyright : String? = null
    @SerializedName("attributionText") val attributionText : String? = null
    @SerializedName("etag") val etag : String? = null
    @SerializedName("data") val results :Any? = null
}

data class Data(
    @SerializedName("total") val total : Int?,
    @SerializedName("count") val count : Int?,
    @SerializedName("results") val results : JSONArray?
)

data class GenericResponse(
    @SerializedName("code") val code : Int = 0,
    @SerializedName("description") val description : String? = ""
)
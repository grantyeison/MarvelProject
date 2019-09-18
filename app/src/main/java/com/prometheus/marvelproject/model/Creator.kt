package com.prometheus.marvelproject.model


import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.Serializable

class Creator (
    @SerializedName("id") val id: Int? = -1,
    @SerializedName("fullName") val title: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("series") val series: String? = "",
    @SerializedName("comics") val comics: String? = "",
    @SerializedName("events") val events: String? = "",
    @SerializedName("creators") val creators: String? = "",
    @SerializedName("characters") val characters: String? = "",
    @SerializedName("stories") val stories: String? = ""
): Serializable{

    override fun equals(other: Any?): Boolean {
        if (other is Creator) {
            if (this.id != -1) {
                return this.id == other.id
            }
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return this.id.toString()+ " - " + this.title + " : "+this.description
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}

class CreatorsResult : ServiceResult()

data class CreatorFiltered(val Creators: MutableList<Creator>?)


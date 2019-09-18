package com.prometheus.marvelproject.model


import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.Serializable

class Character (
    @SerializedName("id") var id: Int? = -1,
    @SerializedName("name") var name: String? = "",
    @SerializedName("description") var description: String? = "",
    @SerializedName("series") var series: String? = "",
    @SerializedName("comics") var comics: String? = "",
    @SerializedName("events") var events: String? = "",
    @SerializedName("creators") var creators: String? = "",
    @SerializedName("characters") var characters: String? = "",
    @SerializedName("stories") var stories: String? = ""
): Serializable{

    override fun equals(other: Any?): Boolean {
        if (other is Character) {
            if (this.id != -1) {
                return this.id == other.id
            }
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return toJson().toString()
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }

    fun toJson(): JSONObject{
        val obj = JSONObject()

        obj.put("id",id)
        obj.put("name",name)
        obj.put("description",description)
        obj.put("series",series)
        obj.put("comics",comics)
        obj.put("events",events)
        obj.put("creators",creators)
        obj.put("characters",characters)
        obj.put("stories",stories)

        return obj
    }

    fun toObject(obj: JSONObject){

        this.id = obj.getInt("id")
        this.name = obj.getString("name")
        this.description = obj.getString("description")
        this.series = obj.getString("series")
        this.comics = obj.getString("comics")
        this.events = obj.getString("events")
        this.creators = obj.getString("creators")
        this.characters = obj.getString("characters")
        this.stories = obj.getString("stories")

    }
}

class CharacterResult : ServiceResult()

data class CharacterFiltered(val characters: MutableList<Character>?)


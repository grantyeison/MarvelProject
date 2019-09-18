package com.prometheus.marvelproject.ui.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.prometheus.marvelproject.application.MarvelProjectApp
import com.prometheus.marvelproject.model.Creator
import com.prometheus.marvelproject.model.CreatorFiltered
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CreatorViewModel : BaseViewModel() {

    val simpleCache = MarvelProjectApp.appComponent.simpleCache()
    val PUBLIC_KEY = simpleCache.getStringValue("PUBLIC_KEY")
    val PRIVATE_KEY = simpleCache.getStringValue("PRIVATE_KEY")
    val onCreatorsListLoaded : MutableLiveData<CreatorFiltered> by lazy {
        MutableLiveData<CreatorFiltered>()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun loadCreators(){
        val ts = (System.currentTimeMillis() / 1000).toString()
        val hash = simpleCache.md5(ts + PRIVATE_KEY + PUBLIC_KEY)
        disposables.add(
            MarvelProjectApp.appComponent.MarvelService()
                .getCreators(ts,PUBLIC_KEY!!,hash)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val result = it.results
                    val resultData = obtainItems(result!!)
                    onCreatorsListLoaded.value = CreatorFiltered(resultData)
                },{
                    it.printStackTrace()
                    onError.value = "Error"
                })
        )
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun obtainItems(result: Any): MutableList<Creator>? {
        var items = mutableListOf<Creator>()
        val gson = Gson()
        val jsonObject = gson.toJsonTree(result).getAsJsonObject()

        val list = jsonObject.getAsJsonArray("results")

        list.forEach { dt ->
            if (dt.isJsonObject) {
                val dtObj = dt.asJsonObject
                var id = -1
                var name = ""
                var description = ""
                var series = JsonObject()
                var comics = JsonObject()
                var events = JsonObject()
                var creators = JsonObject()
                var characters = JsonObject()
                var stories = JsonObject()

                if (dtObj.get("id") != null)
                    id = dtObj.get("id").asInt

                if (dtObj.get("fullName") != null)
                    name = dtObj.get("fullName").asString

                if (dtObj.get("description") != null)
                    description = dtObj.get("description").asString

                if (dtObj.has("series") && dtObj.get("series") != null)
                    series = dtObj.get("series").asJsonObject

                if (dtObj.has("comics") && dtObj.get("comics") != null)
                    comics = dtObj.get("comics").asJsonObject

                if (dtObj.has("events") && dtObj.get("events") != null)
                    events = dtObj.get("events").asJsonObject

                if (dtObj.has("creators") && dtObj.get("creators") != null)
                    creators = dtObj.get("creators").asJsonObject

                if (dtObj.has("characters") && dtObj.get("characters") != null)
                    characters = dtObj.get("characters").asJsonObject

                if (dtObj.has("stories") && dtObj.get("stories") != null)
                    stories = dtObj.get("stories").asJsonObject

                val singleItem = Creator(id,name,description,series.toString(),comics.toString(),events.toString(),creators.toString(),characters.toString(),stories.toString())
                items.add(singleItem)
            }
        }
        return items
    }
}

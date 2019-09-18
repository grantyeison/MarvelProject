package com.prometheus.marvelproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.google.gson.JsonObject
import com.prometheus.marvelproject.application.MarvelProjectApp
import com.prometheus.marvelproject.model.Character
import com.prometheus.marvelproject.ui.Adapter.sublistItemAdapter
import com.prometheus.marvelproject.ui.dialog.sublistDetailDialog
import kotlinx.android.synthetic.main.activity_item_detail.*
import org.json.JSONObject

class itemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        var serviceCalled = JSONObject()

        if (MarvelProjectApp.appComponent.simpleCache().contains("characters")) {
            var item = Character(-1,"","","","","","","","")
            serviceCalled = JSONObject(MarvelProjectApp.appComponent.simpleCache().getStringValue("characters")!!)
         //   item.toObject(serviceCalled)
        }

        val series = JSONObject(serviceCalled.getString("series"))
        val comics = JSONObject(serviceCalled.getString("comics"))
        val events = JSONObject(serviceCalled.getString("events"))
        val creators = JSONObject(serviceCalled.getString("creators"))
        val characters = JSONObject(serviceCalled.getString("characters"))
        val stories = JSONObject(serviceCalled.getString("stories"))

        if (!comics.has("collectionURI"))
            btnDetComics.isVisible = false

        if (!events.has("collectionURI"))
            btnDetEvents.isVisible = false

        if (!creators.has("collectionURI"))
            btnDetCreators.isVisible = false

        if (!characters.has("collectionURI"))
            btnDetChars.isVisible = false

        if (!stories.has("collectionURI"))
            btnDetStories.isVisible = false

        if (!series.has("collectionURI"))
            btnDetSeries.isVisible = false

        btnDetSeries.setOnClickListener{
            MarvelProjectApp.appComponent.simpleCache().setValue("list",series.toString())
            val sublistDetailFragment = sublistDetailDialog.newInstance()
            sublistDetailFragment.show(supportFragmentManager,"")
        }
    }
}

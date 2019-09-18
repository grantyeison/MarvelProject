package com.prometheus.marvelproject.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.prometheus.marvelproject.R
import com.prometheus.marvelproject.application.MarvelProjectApp
import com.prometheus.marvelproject.itemDetailActivity
import com.prometheus.marvelproject.model.*
import com.prometheus.marvelproject.ui.Adapter.*
import com.prometheus.marvelproject.ui.viewModel.*
import com.prometheus.marvelproject.utilities.OnAdapterItemClick
import kotlinx.android.synthetic.main.activity_response.*

class ResponseActivity : BaseActivity(), OnAdapterItemClick {

    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var comicViewModel: ComicViewModel
    private lateinit var creatorViewModel: CreatorViewModel
    private lateinit var eventViewModel: EventViewModel
    private lateinit var serieViewModel: SerieViewModel
    private lateinit var storyViewModel: StoryViewModel

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        val extras = intent.extras
        var response = 0

        if (extras != null) {
            response = extras.getInt("response")
        }

        callService(response)

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun callService(response: Int) {

        var left = -1

        if (response == 0) {
            txtResponseTitle.setText(R.string.txtTitleCharacters)
            rclMarvelItemList.layoutManager = LinearLayoutManager(this)
            rclMarvelItemList.adapter = CharacterAdapter(mutableListOf(),this,this)
            initCharactersViewModel()
            characterViewModel.loadCharacters()
        } else {
            left = response % 3
            if (left == 0) {
                txtResponseTitle.setText(R.string.txtTitleComics)
                rclMarvelItemList.layoutManager = LinearLayoutManager(this)
                rclMarvelItemList.adapter = ComicAdapter(mutableListOf(),this,this)
                initComicsViewModel()
                comicViewModel.loadComics()
            } else {
                left = response % 5
                if (left == 0) {
                    txtResponseTitle.setText(R.string.txtTitleComics)
                    rclMarvelItemList.layoutManager = LinearLayoutManager(this)
                    rclMarvelItemList.adapter = ComicAdapter(mutableListOf(),this,this)
                    initComicsViewModel()
                    comicViewModel.loadComics()
                } else {
                    left = response % 7
                    if (left == 0) {
                        txtResponseTitle.setText(R.string.txtTitleCreators)
                        rclMarvelItemList.layoutManager = LinearLayoutManager(this)
                        rclMarvelItemList.adapter = CreatorAdapter(mutableListOf(),this,this)
                        initCreatorViewModel()
                        creatorViewModel.loadCreators()
                    } else {
                        left = response % 11
                        if (left == 0) {
                            txtResponseTitle.setText(R.string.txtTitleEvents)
                            rclMarvelItemList.layoutManager = LinearLayoutManager(this)
                            rclMarvelItemList.adapter = EventAdapter(mutableListOf(),this,this)
                            initEventViewModel()
                            eventViewModel.loadEvents()
                        } else {
                            left = response % 13
                            if (left == 0) {
                                txtResponseTitle.setText(R.string.txtTitleSeries)
                                rclMarvelItemList.layoutManager = LinearLayoutManager(this)
                                rclMarvelItemList.adapter = SerieAdapter(mutableListOf(),this,this)
                                initSerieViewModel()
                                serieViewModel.loadSeries()
                            } else {
                                txtResponseTitle.setText(R.string.txtTitleStories)
                                rclMarvelItemList.layoutManager = LinearLayoutManager(this)
                                rclMarvelItemList.adapter = StoryAdapter(mutableListOf(),this,this)
                                initStoryViewModel()
                                storyViewModel.loadStories()
                            }
                        }

                    }

                }

            }

        }
    }

    private fun initStoryViewModel() {
        storyViewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)
        storyViewModel.onStoriesListLoaded.observe(this, Observer { itemsFound ->
            val adapter = rclMarvelItemList.adapter as StoryAdapter
            adapter.setItems(itemsFound.Stories!!)
        })
    }

    private fun initSerieViewModel() {
        serieViewModel = ViewModelProviders.of(this).get(SerieViewModel::class.java)
        serieViewModel.onSeriesListLoaded.observe(this, Observer { itemsFound ->
            val adapter = rclMarvelItemList.adapter as SerieAdapter
            adapter.setItems(itemsFound.Series!!)
        })
    }

    private fun initEventViewModel() {
        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)
        eventViewModel.onEventsListLoaded.observe(this, Observer { itemsFound ->
            val adapter = rclMarvelItemList.adapter as EventAdapter
            adapter.setItems(itemsFound.Events!!)
        })
    }

    private fun initCreatorViewModel() {
        creatorViewModel = ViewModelProviders.of(this).get(CreatorViewModel::class.java)
        creatorViewModel.onCreatorsListLoaded.observe(this, Observer { itemsFound ->
            val adapter = rclMarvelItemList.adapter as CreatorAdapter
            adapter.setItems(itemsFound.Creators!!)
        })
    }

    private fun initComicsViewModel() {
        comicViewModel = ViewModelProviders.of(this).get(ComicViewModel::class.java)
        comicViewModel.onComicsListLoaded.observe(this, Observer { itemsFound ->
            val adapter = rclMarvelItemList.adapter as ComicAdapter
            adapter.setItems(itemsFound.Comics!!)
        })
    }

    private fun initCharactersViewModel() {
        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        characterViewModel.onCharacterListLoaded.observe(this, Observer { itemsFound ->
            val adapter = rclMarvelItemList.adapter as CharacterAdapter
            adapter.setItems(itemsFound.characters!!)
        })
    }

    override fun onItemClick(item: Any) {

        when(item){
            is Character ->
            {
                MarvelProjectApp.appComponent.simpleCache().setValue("characters",item.toString())
            }
            is Comic ->{}
            is Creator ->{}
            is Event ->{}
            is Serie ->{}
            is Story ->{}
        }
        val intent = Intent(this, itemDetailActivity::class.java)
        startActivity(intent)

    }

}

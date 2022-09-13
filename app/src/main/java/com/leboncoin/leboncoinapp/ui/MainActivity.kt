package com.leboncoin.leboncoinapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.leboncoin.domain.RequestResult
import com.leboncoin.leboncoinapp.databinding.ActivityMainBinding
import com.leboncoin.leboncoinapp.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private var albumAdapter = AlbumAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)
        val uiBindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(uiBindings.root)

        //
        setUpUi(uiBindings)
        observeFetchAlbums(uiBindings)
        mainViewModel.fetchAlbums()
    }

    private fun setUpUi(uiBindings: ActivityMainBinding) {
        uiBindings.albumRecyclerView.apply {
            layoutManager = GridLayoutManager(
                context,
                2
            )
            adapter = albumAdapter
            addItemDecoration(AlbumItemDecoration(context))
        }
    }

    private fun observeFetchAlbums(uiBindings: ActivityMainBinding) {
        mainViewModel.albumsLiveData.observe(this,
            Observer {
                when (it) {
                    is RequestResult.Success -> {
                        albumAdapter.submitList(it.result)
                        uiBindings.albumRecyclerView.visibility = View.VISIBLE
                        uiBindings.errorText.visibility = View.INVISIBLE
                    }

                    is RequestResult.Error -> {
                        uiBindings.albumRecyclerView.visibility = View.INVISIBLE
                        uiBindings.errorText.visibility = View.VISIBLE
                    }
                }
            })
    }
}
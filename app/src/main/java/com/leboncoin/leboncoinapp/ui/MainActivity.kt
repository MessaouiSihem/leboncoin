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
        mainViewModel.albumsLiveData.observe(this
        ) {
            when (it) {
                is RequestResult.Success -> {
                    if (it.result.isEmpty()) {
                        // Fetched list is empty
                        hideDate(uiBindings)
                    } else {
                        albumAdapter.submitList(it.result)
                        showData(uiBindings)
                    }
                }

                is RequestResult.Error -> {
                    hideDate(uiBindings)
                }
            }
        }
    }

    private fun showData(uiBindings: ActivityMainBinding) {
        uiBindings.albumRecyclerView.visibility = View.VISIBLE
        uiBindings.errorText.visibility = View.INVISIBLE
        uiBindings.progressIndicator.visibility = View.INVISIBLE
    }

    private fun hideDate(uiBindings: ActivityMainBinding) {
        uiBindings.albumRecyclerView.visibility = View.INVISIBLE
        uiBindings.errorText.visibility = View.VISIBLE
        uiBindings.progressIndicator.visibility = View.INVISIBLE
    }
}
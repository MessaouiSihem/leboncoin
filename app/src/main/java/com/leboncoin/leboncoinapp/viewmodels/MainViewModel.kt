package com.leboncoin.leboncoinapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leboncoin.domain.RequestResult
import com.leboncoin.domain.models.Album
import com.leboncoin.domain.usecase.FetchAlbumsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val fetchAlbumsUseCase: FetchAlbumsUseCase
) : ViewModel() {

    private val _albumsLiveData = MutableLiveData<RequestResult<List<Album>>>()
    val albumsLiveData: LiveData<RequestResult<List<Album>>> = _albumsLiveData

    fun fetchAlbums() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                fetchAlbumsUseCase.execute()
            }
            _albumsLiveData.value = result
        }
    }
}
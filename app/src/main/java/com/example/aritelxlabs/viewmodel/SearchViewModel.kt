package com.example.aritelxlabs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.repository.Result
import com.example.search.repository.SearchRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class SearchViewModel(private val baseDataSource: SearchRepository) : ViewModel() {

    private val _suggestions = MutableLiveData<JsonObject>()
    val suggestions: LiveData<JsonObject> = _suggestions


    fun getSuggestions(query: String, city: String) {
        viewModelScope.launch {
            when (val result = baseDataSource.getSearchSuggestions(query, city)) {
                is Result.Success -> _suggestions.postValue(result.data)
            }
        }
    }
}
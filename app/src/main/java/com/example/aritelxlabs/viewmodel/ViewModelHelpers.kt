package com.example.aritelxlabs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.search.api.DataSource
import com.example.search.repository.SearchRepository

fun getViewModelFactory(): ViewModelFactory {
    val repository = SearchRepository.getInstance(DataSource.newInstance())
    return ViewModelFactory(repository)
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val searchRepository: SearchRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(SearchViewModel::class.java) ->
                    SearchViewModel(searchRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
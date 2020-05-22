package com.example.search.api

import com.example.search.repository.Result
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class DataSource private constructor(private val apiService: ApiService) : BaseDataSource {

    override suspend fun getSearchSuggestions(query: String, city: String): com.example.search.repository.Result<JsonObject> = withContext(Dispatchers.IO) {
        val request = apiService.getSearchSuggestionsAsync(query, city)
        try {
            val response = request.await()
            when {
                response != null -> com.example.search.repository.Result.Success(response)
                else -> com.example.search.repository.Result.Error(RemoteDataNotFoundException())
            }
        } catch (ex: Throwable) {
            Result.Error(ex)
        }

    }

    companion object {
        fun newInstance() = DataSource(ApiService.create())
    }
}

package com.example.search.api

import com.example.search.repository.Result
import com.google.gson.JsonObject


interface BaseDataSource {

    suspend fun getSearchSuggestions(query: String, city: String): Result<JsonObject>

}
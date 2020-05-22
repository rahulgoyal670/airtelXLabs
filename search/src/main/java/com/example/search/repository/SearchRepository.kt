package com.example.search.repository

import com.example.search.api.BaseDataSource
import com.google.gson.JsonObject


class SearchRepository(private val baseDataSource: BaseDataSource) {

    suspend fun getSearchSuggestions(query: String, city: String): Result<JsonObject> {
        return baseDataSource.getSearchSuggestions(query, city)
    }

    companion object {
        private var INSTANCE: SearchRepository? = null

        fun getInstance(remoteDataSource: BaseDataSource): SearchRepository {
            return INSTANCE ?: SearchRepository(remoteDataSource).apply { INSTANCE = this }
        }
    }
}
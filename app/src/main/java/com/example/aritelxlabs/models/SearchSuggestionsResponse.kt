package com.example.aritelxlabs.models

data class SearchSuggestionsResponse(
    val requestId: String,
    val data: Data
)

data class Data(
    val autoCompleteRequestString: String,
    val focusWord: String,
    val addressList: ArrayList<AddressModel>
)
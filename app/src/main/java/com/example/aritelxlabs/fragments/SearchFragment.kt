package com.example.aritelxlabs.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aritelxlabs.R
import com.example.aritelxlabs.adapter.SearchListAdapter
import com.example.aritelxlabs.models.AddressModel
import com.example.aritelxlabs.models.SearchSuggestionsResponse
import com.example.aritelxlabs.viewmodel.SearchViewModel
import com.example.aritelxlabs.viewmodel.getViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.search_fargment.*

public class SearchFragment : Fragment() {

    private var adaper: SearchListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fargment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel by viewModels<SearchViewModel> { getViewModelFactory() }


        val myList = ArrayList<AddressModel>()
        adaper = SearchListAdapter(myList)
        suggestionsRV.adapter = adaper
        suggestionsRV.layoutManager = LinearLayoutManager(context)
        viewModel.suggestions.observe(viewLifecycleOwner, Observer { res ->
            res?.let {
                var data = Gson().fromJson(it, SearchSuggestionsResponse::class.java)
                myList.clear()
                myList.addAll(data.data.addressList)
                adaper!!.notifyDataSetChanged()
            }
        })


        searchInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                viewModel.getSuggestions(s.toString(), citySpinner.selectedItem.toString())
            }
        })
    }

}
package com.example.aritelxlabs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aritelxlabs.R
import com.example.aritelxlabs.models.AddressModel


class SearchListAdapter(private var list: List<AddressModel>) :
    RecyclerView.Adapter<SearchListAdapter.SearchViewHolder>() {

    var searches: List<AddressModel> = list
        set(searches) {
            field = searches
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item_layout, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val address: AddressModel = searches[position]
        holder.labelTV.text = address.label + "(City: " + address.city + ")"
        holder.addressTV.text = address.addressString
    }

    fun replaceData(suggestion: List<AddressModel>) {
        searches = suggestion
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = searches.size

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var labelTV = itemView.findViewById<TextView>(R.id.labelTV)
        var addressTV = itemView.findViewById<TextView>(R.id.addressTV)
    }

}
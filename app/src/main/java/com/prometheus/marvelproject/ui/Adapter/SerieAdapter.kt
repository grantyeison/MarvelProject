package com.prometheus.marvelproject.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prometheus.marvelproject.R
import com.prometheus.marvelproject.model.Serie
import com.prometheus.marvelproject.ui.Holder.ItemHolder
import com.prometheus.marvelproject.utilities.OnAdapterItemClick


class SerieAdapter (
    private var items : MutableList<Serie>,
    private val context: Context,
    private val onItemClickListener: OnAdapterItemClick?
) : RecyclerView.Adapter<ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(context).inflate(R.layout.marvel_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.txtName.text = item.title
        holder.txtDesc.text = item.description
        this.onItemClickListener?.let {
            holder.itemView.setOnClickListener { onItemClickListener.onItemClick(item) }
        }
    }

    fun setItems(newItems: MutableList<Serie>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun isItemListEmpty(): Boolean {
        return items.isEmpty()
    }
}
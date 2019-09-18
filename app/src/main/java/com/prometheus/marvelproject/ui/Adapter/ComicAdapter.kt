package com.prometheus.marvelproject.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prometheus.marvelproject.R
import com.prometheus.marvelproject.model.Comic
import com.prometheus.marvelproject.ui.Holder.ItemHolder
import com.prometheus.marvelproject.utilities.OnAdapterItemClick
import io.reactivex.subjects.PublishSubject


class ComicAdapter (
    private var items : MutableList<Comic>,
    private val context: Context,
    private val onItemClickListener: OnAdapterItemClick?
) : RecyclerView.Adapter<ItemHolder>(){

    val onLocationSelected = PublishSubject.create<Comic>()

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



    fun setItems(newItems: MutableList<Comic>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun isItemListEmpty(): Boolean {
        return items.isEmpty()
    }
}
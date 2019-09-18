package com.prometheus.marvelproject.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prometheus.marvelproject.R
import com.prometheus.marvelproject.ui.Holder.ItemHolder
import io.reactivex.subjects.PublishSubject


class sublistItemAdapter (
    private var items : MutableList<String>,
    private val context: Context
) : RecyclerView.Adapter<ItemHolder>(){


    val onItemsSelected = PublishSubject.create<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(context).inflate(R.layout.marvel_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.txtName.text = item
    }



    fun setItems(newItems: MutableList<String>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun isItemListEmpty(): Boolean {
        return items.isEmpty()
    }
}
package com.prometheus.marvelproject.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prometheus.marvelproject.R
import com.prometheus.marvelproject.model.Character
import com.prometheus.marvelproject.ui.Holder.ItemHolder
import com.prometheus.marvelproject.utilities.OnAdapterItemClick
import io.reactivex.subjects.PublishSubject


class CharacterAdapter (
    private var items : MutableList<Character>,
    private val context: Context,
    private val onItemClickListener: OnAdapterItemClick?
) : RecyclerView.Adapter<ItemHolder>(){

    val onLocationSelected = PublishSubject.create<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(context).inflate(R.layout.marvel_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.txtName.text = item.name
        holder.txtDesc.text = item.description
        // holder.tvType.text = location.type
        this.onItemClickListener?.let {
            holder.itemView.setOnClickListener { onItemClickListener.onItemClick(item) }
        }
    }



    fun setItems(newItems: MutableList<Character>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun isItemListEmpty(): Boolean {
        return items.isEmpty()
    }
}
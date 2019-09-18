package com.prometheus.marvelproject.ui.Holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.marvel_item.view.*

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtName = view.txtName
    val txtDesc = view.txtDesc
}
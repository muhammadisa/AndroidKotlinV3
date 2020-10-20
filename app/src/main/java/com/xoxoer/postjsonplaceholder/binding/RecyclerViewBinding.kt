package com.xoxoer.postjsonplaceholder.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.postjsonplaceholder.util.AdapterContract

@BindingAdapter("adapter", "orientation")
fun bindRecyclerViewAdapter(
    view: RecyclerView,
    adapters: RecyclerView.Adapter<*>,
    orientation: Int
) {
    view.adapter = adapters
    view.setHasFixedSize(true)
    view.layoutManager = LinearLayoutManager(
        view.context,
        orientation,
        false
    )
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("adapterList")
fun <R : AdapterContract, D> bindAdapterList(view: RecyclerView, items: List<D>?) {
    if (!items.isNullOrEmpty()) {
        (view.adapter as? R)?.add(items)
    }
}
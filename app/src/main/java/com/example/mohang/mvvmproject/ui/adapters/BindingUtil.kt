package com.example.mohang.mvvm

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.mohang.mvvmproject.ui.adapters.*

/**
 * Created by mohang on 3/7/17.
 */


/*@BindingAdapter("genericLayout", "listItem", "recyclerConfiguration")
fun <T> setAdapterTest(recyclerView: RecyclerView, genericLayout: Int, items: List<T>, recyclerConfiguration: RecyclerConfiguration) {

    var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = recyclerView.adapter
    if (adapter == null) {
        adapter = GenericAdapter<T>(genericLayout, items)
        recyclerView.setHasFixedSize(recyclerConfiguration?.hasFixedSize() ?: true)

        recyclerView.layoutManager = recyclerConfiguration?.layoutManager() ?: LinearLayoutManager(recyclerView.context)
        recyclerView.itemAnimator = recyclerConfiguration?.itemAnimator() ?: DefaultItemAnimator()
        recyclerView.addItemDecoration(recyclerConfiguration?.itemDecoration())
        recyclerView.adapter = adapter
        adapter.updateItems(ArrayList(items))
    } else {
        adapter = adapter as BaseAdapter<T>
        adapter.calculateDiff(items)
    }
}*/

@BindingAdapter(value = *arrayOf("genericLayout","viewTypeHandler", "listItem", "recyclerConfiguration", "actionHandler", "viewListener"), requireAll = false)
fun <T> setAdapterTest(recyclerView: RecyclerView, genericLayout:Int?, viewTypeHandler: ViewTypeHandler?, items: List<T>?, recyclerConfiguration: RecyclerConfiguration?, actionHandler: ActionHandler?, viewListener: ViewListener?) {

    var adapter = recyclerView.adapter



    if (adapter == null) {
       adapter= MyAdapter.Builder(ArrayList(items))
                .withActionHandler(actionHandler)
                .withViewTypeHandler(viewTypeHandler)
                .withViewListener (viewListener)
                .withLayoutRes(genericLayout)
                .withRecyclerConfig(recyclerConfiguration)
                .build()
                adapter.intoList(recyclerView)
    } else {
        adapter = adapter as BaseAdapter<T>
        adapter.calculateDiff(items)
    }
}




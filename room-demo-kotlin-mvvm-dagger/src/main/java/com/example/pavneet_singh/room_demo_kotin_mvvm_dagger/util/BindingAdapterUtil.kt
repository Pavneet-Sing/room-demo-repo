package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.base.BindingAdapterI

/**
 * Created by Pavneet_Singh on 2020-01-29.
 */

@BindingAdapter("data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    data?.let {
        if (recyclerView.adapter is BindingAdapterI<*>) {
            @Suppress("UNCHECKED_CAST")
            (recyclerView.adapter as BindingAdapterI<T>).setSource(data)
        }
    }
}
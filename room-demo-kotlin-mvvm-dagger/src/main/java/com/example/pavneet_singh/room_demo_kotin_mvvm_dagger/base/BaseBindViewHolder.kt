package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Pavneet_Singh on 2020-01-29.
 */

abstract class BaseBindViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    abstract fun bind(model: T)
}
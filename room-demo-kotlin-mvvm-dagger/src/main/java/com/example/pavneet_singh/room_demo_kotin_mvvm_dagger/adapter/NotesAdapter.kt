package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.R
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

class NotesAdapter(
    private val list: List<Note>,
    context: Context
) :
    RecyclerView.Adapter<NotesAdapter.BeanHolder?>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val onNoteItemClick: OnNoteItemClick
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeanHolder {
        val view: View = layoutInflater.inflate(R.layout.note_list_item, parent, false)
        return BeanHolder(view)
    }

    override fun onBindViewHolder(
        holder: BeanHolder,
        position: Int
    ) {
        Log.e("bind", "onBindViewHolder: " + list[position])
        holder.textViewTitle.text = list[position].title
        holder.textViewContent.text = list[position].content
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class BeanHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewContent: TextView
        val textViewTitle: TextView
        override fun onClick(view: View) {
            onNoteItemClick.onNoteClick(adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
            textViewContent = itemView.findViewById(R.id.item_text)
            textViewTitle = itemView.findViewById(R.id.tv_title)
        }
    }

    interface OnNoteItemClick {
        fun onNoteClick(pos: Int)
    }

    init {
        onNoteItemClick = context as OnNoteItemClick
    }
}
package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.R
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.base.BaseBindViewHolder
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.base.BindingAdapterI
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.databinding.NoteListItemBinding
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

class NotesAdapter(
        private var list: List<Note>,
        context: Context
) :
        RecyclerView.Adapter<NotesAdapter.NoteHolder?>(), BindingAdapterI<List<Note>> {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val onNoteItemClick: OnNoteItemClick
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): NoteHolder {
        return NoteHolder(
                DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.note_list_item,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(
            holder: NoteHolder,
            position: Int
    ) = holder.bind(list[position])

    override fun getItemCount(): Int {
        return list.size
    }

    inner class NoteHolder(private val binding: NoteListItemBinding) :
            BaseBindViewHolder<Note>(binding.root) {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onNoteItemClick.onNoteClick(list[adapterPosition])
        }

        override fun bind(model: Note) {
            binding.note = model
        }

    }

    interface OnNoteItemClick {
        fun onNoteClick(note: Note)
    }

    init {
        onNoteItemClick = context as OnNoteItemClick
    }

    override fun setSource(data: List<Note>) {
        list = data
        notifyDataSetChanged()
    }
}
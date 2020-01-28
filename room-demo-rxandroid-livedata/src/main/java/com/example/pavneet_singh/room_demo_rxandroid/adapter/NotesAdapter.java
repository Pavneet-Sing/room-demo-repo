package com.example.pavneet_singh.room_demo_rxandroid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pavneet_singh.room_demo_rxandroid.R;
import com.example.pavneet_singh.room_demo_rxandroid.notedb.model.Note;

import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * Created by Pavneet_Singh on 12/20/17.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.BeanHolder> {

    private final List<Note> list;
    private final LayoutInflater layoutInflater;
    private final OnNoteItemClick onNoteItemClick;

    public NotesAdapter(List<Note> list, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.onNoteItemClick = (OnNoteItemClick) context;
    }


    @NotNull
    @Override
    public BeanHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_list_item, parent, false);
        return new BeanHolder(view);
    }

    @Override
    public void onBindViewHolder(BeanHolder holder, int position) {
        Log.e("bind", "onBindViewHolder: " + list.get(position));
        holder.textViewTitle.setText(list.get(position).getTitle());
        holder.textViewContent.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BeanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView textViewContent;
        final TextView textViewTitle;

        BeanHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewContent = itemView.findViewById(R.id.item_text);
            textViewTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View view) {
            onNoteItemClick.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteItemClick {
        void onNoteClick(int pos);
    }
}
package com.example.pavneet_singh.room_demo_rxandroid.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pavneet_singh.room_demo_rxandroid.R;
import com.example.pavneet_singh.room_demo_rxandroid.adapter.NotesAdapter;
import com.example.pavneet_singh.room_demo_rxandroid.notedb.NoteDatabase;
import com.example.pavneet_singh.room_demo_rxandroid.notedb.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity implements NotesAdapter.OnNoteItemClick {

    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private NoteDatabase noteDatabase;
    private List<Note> notes;
    private NotesAdapter notesAdapter;
    private int pos;
    private AlertDialog optionDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVies();
        displayList();
    }

    /**
     * Setup the database and live observer to show the list
     * You can use anonymous class instead of
     * <a href="https://developer.android.com/studio/write/java8-support#supported_features">Lambda expressions or Method reference</a> for observer as
     * <pre>{@code
     * noteDatabase
     *     .getNoteDao()
     *     .getNotes()
     *     .observe(NoteListActivity.this, new Observer<List<Note>>() {
     *
     *     @Override
     *     public void onChanged(List<Note> notes) {
     *          // process notes result
     *     }
     * }}</pre>
     */
    private void displayList() {
        noteDatabase = NoteDatabase.getInstance(NoteListActivity.this);
        noteDatabase.getNoteDao().getNotes().observe(NoteListActivity.this, this::updateList);
    }

    private void initializeVies() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewMsg = findViewById(R.id.tv__empty);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(listener);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(NoteListActivity.this));
        notes = new ArrayList<>();
        notesAdapter = new NotesAdapter(notes, NoteListActivity.this);
        recyclerView.setAdapter(notesAdapter);
        optionDialog = getItemOptionBuilder();
    }

    private View.OnClickListener listener = view -> startActivityForResult(new Intent(NoteListActivity.this, AddNoteActivity.class), 100);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode > 0) {
            if (resultCode == 1) {
                notes.add((Note) data.getSerializableExtra("note"));
            } else if (resultCode == 2) {
                notes.set(pos, (Note) data.getSerializableExtra("note"));
            }
            listVisibility();
        }
    }

    @Override
    public void onNoteClick(final int pos) {
        this.pos = pos;
        optionDialog.show();
    }

    private AlertDialog getItemOptionBuilder() {
        return new AlertDialog.Builder(NoteListActivity.this)
                .setTitle("Select Options")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                noteDatabase.getNoteDao().deleteNote(notes.get(pos));
                                notes.remove(pos);
                                listVisibility();
                                break;
                            case 1:
                                startActivityForResult(
                                        new Intent(NoteListActivity.this,
                                                AddNoteActivity.class).putExtra("note", notes.get(pos)),
                                        100);

                                break;
                        }
                    }
                }).create();
    }

    private void updateList(List<Note> noteList) {
        if (noteList != null && (!noteList.isEmpty())) {
            notes.clear();
            notes.addAll(noteList);
            // hides empty text view
            textViewMsg.setVisibility(View.GONE);
            notesAdapter.notifyDataSetChanged();
        }
    }

    private void listVisibility() {
        int emptyMsgVisibility = View.GONE;
        if (notes.isEmpty()) { // no item to display
            if (textViewMsg.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }
        textViewMsg.setVisibility(emptyMsgVisibility);
        notesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        noteDatabase.cleanUp();
        if (optionDialog.isShowing())
            optionDialog.dismiss();
        super.onDestroy();
    }
}

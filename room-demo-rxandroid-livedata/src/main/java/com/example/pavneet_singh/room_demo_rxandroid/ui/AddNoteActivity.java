package com.example.pavneet_singh.room_demo_rxandroid.ui;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pavneet_singh.room_demo_rxandroid.R;
import com.example.pavneet_singh.room_demo_rxandroid.notedb.NoteDatabase;
import com.example.pavneet_singh.room_demo_rxandroid.notedb.model.Note;
import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddNoteActivity extends AppCompatActivity {

    private TextInputEditText et_title, et_content;
    private NoteDatabase noteDatabase;
    private Note note;
    private boolean update;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        noteDatabase = NoteDatabase.getInstance(AddNoteActivity.this);
        Button button = findViewById(R.id.but_save);
        if ((note = (Note) getIntent().getSerializableExtra("note")) != null) {
            getSupportActionBar().setTitle("Update Note");
            update = true;
            button.setText("Update");
            et_title.setText(note.getTitle());
            et_content.setText(note.getContent());
        }
        button.setOnClickListener(view -> {
            if (update) {
                note.setContent(et_content.getText().toString());
                note.setTitle(et_title.getText().toString());
                noteDatabase.getNoteDao().updateNote(note);
                finish();
            } else {
                note = new Note(et_content.getText().toString(), et_title.getText().toString());
                disposable = noteDatabase.getNoteDao().insertNote(note)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(rowId -> {
                            note.setNote_id(rowId);
                            finish();
                        }, Throwable::printStackTrace);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (disposable != null)
            disposable.dispose();
        super.onDestroy();
    }
}

package com.example.pavneet_singh.room_demo_rxandroid.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pavneet_singh.room_demo_rxandroid.R;
import com.example.pavneet_singh.room_demo_rxandroid.notedb.NoteDatabase;
import com.example.pavneet_singh.room_demo_rxandroid.notedb.model.Note;
import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AddNoteActivity extends AppCompatActivity {

    private TextInputEditText et_title, et_content;
    private NoteDatabase noteDatabase;
    private Note note;
    private boolean update;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initUI();
    }

    private void initUI() {
        compositeDisposable = new CompositeDisposable();
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
                compositeDisposable.add(noteDatabase.getNoteDao().updateNote(note)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::finish, e -> {
                            Toast.makeText(this, "Update failed due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }));
            } else {
                note = new Note(et_content.getText().toString(), et_title.getText().toString());
                compositeDisposable.add(noteDatabase.getNoteDao().insertNote(note)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        // Can replace rowId -> finish() with this::finish by changing Single to Completable in DAO for insert
                        .subscribe(rowId -> finish(), Throwable::printStackTrace));
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}

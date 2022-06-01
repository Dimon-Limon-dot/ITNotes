package com.example.itnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDbHelper;
    SQLiteDatabase mDb;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvNotes);
        mDbHelper = new DatabaseHelper(this);
        mDb = mDbHelper.getWritableDatabase();
        Button btnAdd  = findViewById(R.id.btnAdd);
        Refresh();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddEditNote.class);
                startActivity(intent);
            }
        });
    }

    void Refresh()
    {
        String data = "";
        Cursor cursor = mDb.rawQuery("SELECT * FROM Note", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            data += cursor.getString(0) + ".";
            data += cursor.getString(1) + ".\n";
            cursor.moveToNext();
        }
        cursor.close();
        textView.setText(data);
    }
}
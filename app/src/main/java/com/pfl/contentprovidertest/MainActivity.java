package com.pfl.contentprovidertest;

import androidx.appcompat.app.AppCompatActivity;

import androidx.room.Room;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.pfl.contentprovidertest.databinding.ActivityMainBinding;
import com.pfl.contentprovidertest.db.AppDatabase;
import com.pfl.contentprovidertest.db.EntityElement;
import com.pfl.contentprovidertest.db.MyDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //AppDatabase db;
    //MyDao myDao;
    //List<EntityElement> entityElements;

    private static final Uri CONTENT_URI = Uri.parse("content://com.pfl.contentprovidertest/table");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        /*db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "content_provider_db")
                .allowMainThreadQueries()
                .build();
        myDao = db.myDao();*/


        updateUI();



        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //myDao.insert(new EntityElement(binding.editText.getText().toString()));
                //updateUI();

            }
        });
    }
    private void updateUI() {
        binding.textView.setText("");
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            binding.textView.append(cursor.getString(cursor.getColumnIndexOrThrow("id")) + " " + cursor.getString(cursor.getColumnIndexOrThrow("text")) + "\n");
        }
        cursor.close();

        /*entityElements = myDao.getAll();
        binding.textView.setText("");
        for (EntityElement e : entityElements) {
            binding.textView.append(e.getText() + "\n");
        }*/
    }
}
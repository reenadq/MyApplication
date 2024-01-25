package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

private EditText etAuthorName;
private EditText etBookTitle;
private EditText etBookCategory;
private Button btnSubmit;

// Database helper instance
private DatabaseHelper databaseHelper;

@Override
 protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

etAuthorName = findViewById(R.id.etAuthorName);
etBookTitle = findViewById(R.id.etBookTitle);
etBookCategory = findViewById(R.id.etBookCategory);
btnSubmit = findViewById(R.id.btnSubmit);

databaseHelper = new DatabaseHelper(this);

 btnSubmit.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
String authorName = etAuthorName.getText().toString();
String bookTitle = etBookTitle.getText().toString();
String bookCategory = etBookCategory.getText().toString();

 // Insert data into the database
databaseHelper.insertBook(authorName, bookTitle, bookCategory);

// Start BookTitlesActivity
Intent intent = new Intent(MainActivity.this, BookTitlesActivity.class);
startActivity(intent);
    }
   });
  }
}
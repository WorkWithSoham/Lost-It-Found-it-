package com.example.lostitfoundit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CreatePostActivity extends AppCompatActivity {

    TextView backBtn;
    Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        backBtn = (TextView) findViewById(R.id.createPostBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewList();
            }
        });
        postBtn = (Button) findViewById(R.id.postBtn);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView itemNameTextView = (TextView) findViewById(R.id.itemName);
                TextView itemDescTextView = (TextView) findViewById(R.id.itemDescription);
                TextView reportedDateTextView = (TextView) findViewById(R.id.reportDate);
                ImageView locationTextView = (ImageView) findViewById(R.id.addLocation);
                ImageView imageTextView = (ImageView) findViewById(R.id.addImage);




                createPost();
            }
        });
    }

    public void goToViewList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void createPost() {

    }
}
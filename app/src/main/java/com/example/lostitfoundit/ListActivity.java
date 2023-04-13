package com.example.lostitfoundit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    ArrayAdapter<String> myAdapter;
    EditText inputSearch;
    ArrayList<HashMap<String, String>> productList;

    public Button createPost;
    private User currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUser = (User) getIntent().getParcelableExtra("currentUser");
        setContentView(R.layout.activity_list);

        List<Post> allPosts = getAllPosts();

        PostAdapter postAdapter = new PostAdapter(this, R.layout.list_item, allPosts);
        ListView myListView = (ListView) findViewById(R.id.editlist_view);
        myListView.setAdapter(postAdapter);

        createPost = (Button) findViewById(R.id.createPostBtn);
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPostActivity();
            }
        });
    }

    public void createPostActivity() {
        Intent intent = new Intent(this, CreatePostActivity.class);
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);
    }

    public List<Post> getAllPosts() {
        MyDatabase myDatabase = MyDatabase.getMyDatabase(this);
        AllDao allDao = myDatabase.getAllDao();

        List<Post> allPosts = allDao.getAllPosts();

        for (Post p : allPosts) {
            System.out.println(p);
        }

        return allPosts;
    }
}
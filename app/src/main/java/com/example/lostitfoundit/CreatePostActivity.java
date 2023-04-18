package com.example.lostitfoundit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;

import android.Manifest;


import java.io.ByteArrayOutputStream;
import java.util.List;

public class CreatePostActivity extends AppCompatActivity {

    TextView backBtn;
    Button postBtn;

    int CAMERA_REQUEST_CODE = 22;
    ImageView cameraBtn;

    ImageView CAMERA;

    int requestCode = 100;

    private User currentUser;

    @Nullable Intent data;

    //private static final int CAMERA_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUser = (User) getIntent().getParcelableExtra("currentUser");
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
                Spinner statusTextView = (Spinner) findViewById(R.id.statusOptions);
                ImageView locationTextView = (ImageView) findViewById(R.id.addLocation);
                ImageView imageTextView = (ImageView) findViewById(R.id.cameraBtn);

                String itemName = itemNameTextView.getText().toString();
                String itemDesc = itemDescTextView.getText().toString();
                String reportedDate = reportedDateTextView.getText().toString();
                String statusString = statusTextView.getSelectedItem().toString();
                Post.STATUS status;

                if (statusString.equalsIgnoreCase(String.valueOf(Post.STATUS.FOUND))) {
                    status = Post.STATUS.FOUND;
                } else {
                    status = Post.STATUS.LOST;
                }

                createPost(itemName, itemDesc, reportedDate, status, data);
            }
        });

        cameraBtn = (ImageView) findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ActivityCompat.checkSelfPermission(CreatePostActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CreatePostActivity.this,new String[]{Manifest.permission.CAMERA}, 22);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
            }
        });


    }

    public void goToViewList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void createPost(String itemName, String itemDesc, String reportedDate, Post.STATUS status, @Nullable Intent data) {
        MyDatabase myDatabase = MyDatabase.getMyDatabase(this);
        AllDao allDao = myDatabase.getAllDao();

        Post createdPost = new Post(currentUser.uid, itemName, itemDesc, "", status, reportedDate, "");
        allDao.createPost(createdPost);

        List<Post> allPosts = allDao.getAllPosts();

        for (Post p : allPosts) {
            System.out.println(p);
        }

        if (requestCode == CAMERA_REQUEST_CODE){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Uri uri = getImageUri(photo);
        }

        goToViewList();
    }
    public Uri getImageUri(Bitmap bitmap){
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, arrayOutputStream);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }
}
package com.example.lostitfoundit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    TextView backBtn;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        backBtn = (TextView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLandingPage();
            }
        });

        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewList();
            }
        });
    }

    public void goToLandingPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToViewList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
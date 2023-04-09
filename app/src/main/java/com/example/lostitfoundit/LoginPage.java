package com.example.lostitfoundit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        login = (Button) findViewById(R.id.signUpBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView emailTextView = (TextView) findViewById(R.id.emailLogin);
                TextView passwordTextView = (TextView) findViewById(R.id.passwordLogin);

                String email = emailTextView.getText().toString();
                String password = passwordTextView.getText().toString();

                goToViewList(email, password);
            }
        });
    }

    public void goToLandingPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToViewList(String email, String password) {
        UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database-name").allowMainThreadQueries().build();

        UserDAO userDAO = db.userDAO();

        User currentUser = userDAO.getUserAtLogin(email, password);

        System.out.println("User at Login: " + currentUser);

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
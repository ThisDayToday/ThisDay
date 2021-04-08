package com.example.thisday;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginPage extends AppCompatActivity {

    private TextView mTextView;
    private EditText etPassword;
    private EditText etUsername;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mTextView = (TextView) findViewById(R.id.text);
        etPassword = findViewById(R.id.etPassword);
        etUsername =findViewById(R.id.etUsername);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username,password);

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });



    }


    private void loginUser(String username, String password){
        Log.i("Login", "Attempting to login" +  username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if(e != null){
                    Log.e("Login", "Issue with Login", e);
                    return;
                }
                gotoMainActivity();


                Toast.makeText(LoginPage.this,"successfully login", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void gotoMainActivity(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }
    private void registerUser(){
        Intent i = new Intent(this, RegisterPage.class);
        startActivity(i);

    }
}
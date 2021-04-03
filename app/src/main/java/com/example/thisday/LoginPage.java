package com.example.thisday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        etUsername =findViewById(R.id.etUsernameR);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });



    }


    private void loginUser(){

        Intent i = new Intent(this, FeedsActivity.class);
        startActivity(i);
    }

    private void registerUser(){
        Intent i = new Intent(this, RegisterPage.class);
        startActivity(i);

    }
}
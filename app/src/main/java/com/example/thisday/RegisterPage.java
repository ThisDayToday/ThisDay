package com.example.thisday;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterPage extends AppCompatActivity {

    private EditText etUsernameR;
    private EditText etPasswordR;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etLocation;
    private Button btnRegisterR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        etUsernameR = findViewById(R.id.etUsernameR);
        etPasswordR = findViewById(R.id.etPasswordR);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etLocation= findViewById(R.id.etLocation);
        btnRegisterR = findViewById(R.id.btnRegisterR);

        btnRegisterR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsernameR.getText().toString();
                String password = etPasswordR.getText().toString();
                String email = etEmail.getText().toString();
                String phone =etPhone.getText().toString();


                checkValid(username, password, email, phone);

            }
        });


    }

    private void checkValid( String username, String password, String email,String phone) {

        ParseUser user = new ParseUser();

        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);
        user.put("phoneNumber", phone);



        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    goAddPic();
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e("SignupActivity", "didnt succeed", e);
                }
            }
        });


    }
    private void goAddPic() {
        Intent i = new Intent(this, AddProfileImageActivity.class);

        startActivity(i);
        finish();
    }

}
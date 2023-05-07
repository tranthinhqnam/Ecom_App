package com.example.appmobileclothes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobileclothes.Models.User;
import com.example.appmobileclothes.Utilities.ValidateUtils;
import com.example.appmobileclothes.ViewModels.UserViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {
    Button registerBtn;
    TextView email, username, password, confirmPass, signIn;
    private DatabaseReference userRef;
    String mEmail, mPassword, mUserName, mConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.RegisterBtn);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPassword);
        signIn = findViewById(R.id.signinText);

        UserViewModel userViewModel = new UserViewModel();

        mEmail = email.getText().toString();
        mPassword = password.getText().toString();
        mUserName = username.getText().toString();
        mConfirm = confirmPass.getText().toString();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateUtils validator = new ValidateUtils();
                if (mEmail.length() > 0 && validator.isValidEmail(mEmail)) {
                    if (validator.isValidLenght(mUserName) && validator.isValidLenght(mPassword)) {
                        if (mConfirm.length() > 0 && validator.isValidConfirmPassword(mConfirm, mPassword)) {
                            String key = userViewModel.getUserKey();
                            User us = new User(key, mUserName, mEmail, mPassword);

                            userViewModel.addUser(us,key, getBaseContext(),"Sign up successfully","Sign up failed");

                            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Incorrect confirm password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Invalid username or password (min_length=6)", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
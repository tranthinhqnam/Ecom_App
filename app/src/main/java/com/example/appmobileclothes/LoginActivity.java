package com.example.appmobileclothes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmobileclothes.Models.User;
import com.example.appmobileclothes.Utilities.ValidateUtils;
import com.example.appmobileclothes.ViewModels.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView register, email, password;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.signinText);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginButton);
        SharedPreferences mPreferences = getSharedPreferences("isLoggin", MODE_PRIVATE);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateUtils validator = new ValidateUtils();
                if (email.getText().toString().length() > 0 && validator.isValidEmail(email.getText().toString())) {
                    if (validator.isValidLenght(password.getText().toString())) {
                        userViewModel.getUsersLiveData().observe(LoginActivity.this, users -> {
                            for (User user : users) {
                                if (email.getText().toString().equals(user.getEmail())) {
                                    if (password.getText().toString().equals(user.getPassword())) {
                                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                        intent.putExtra("id", user.getId());
                                        intent.putExtra("username", user.getName());
                                        intent.putExtra("email", user.getEmail());

                                        //Save login
                                        SharedPreferences.Editor editor = mPreferences.edit();
                                        editor.putBoolean("isLogged", true);
                                        editor.putString("userId", user.getId());
                                        editor.apply();
                                        startActivity(intent);
                                        finish();
                                        break;
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                }
                            }
                        });

                    } else {
                        Toast.makeText(LoginActivity.this, "Minimum password length of 6 characters required", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


}
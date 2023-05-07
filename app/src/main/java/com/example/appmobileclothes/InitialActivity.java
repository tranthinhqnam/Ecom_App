package com.example.appmobileclothes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.appmobileclothes.ViewModels.UserViewModel;

public class InitialActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        SharedPreferences mPreferences = getSharedPreferences("isLoggin", MODE_PRIVATE);
        Boolean isLogged = mPreferences.getBoolean("isLogged", false);
        String userId = mPreferences.getString("userId", "");

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        if (isLogged) {
            userViewModel.getUserByIdFromDb(userId).observe(this, user -> {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("id", user.getId());
                intent.putExtra("username", user.getName());
                intent.putExtra("email", user.getEmail());
                startActivity(intent);
                return;
            });

        } else {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
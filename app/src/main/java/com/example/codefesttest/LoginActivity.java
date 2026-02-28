package com.example.codefesttest;

import static android.provider.Settings.System.putString;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText usernameTextBox;
    private EditText passwordTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        setUI();

        setListeners();
        start();
    }

    protected void start() {
    }

    protected void setListeners() {
        loginButton.setOnClickListener(v -> {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String username = usernameTextBox.getText().toString();
            String password = passwordTextBox.getText().toString();
            Cursor cursor = db.rawQuery("select userID from accounts where username = ? and password = ?", new String[]{username, password});

            if(cursor.moveToFirst()){
                String id = cursor.getString(0);
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("id", id);
                i.putExtra("username", username);
                startActivity(i);

                SharedPreferences prefs = getSharedPreferences("auth", MODE_PRIVATE);
                prefs.edit()
                        .putBoolean("loggedin", true)
                        .putString("id", id).putString("username", username)
                        .apply();
            }else{
                Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //startActivity(new Intent(this, MainActivity.class));

    protected void setUI() {
        loginButton = findViewById(R.id.button_login);
        usernameTextBox = findViewById(R.id.textbox_username);
        passwordTextBox = findViewById(R.id.textbox_password);
    }

}
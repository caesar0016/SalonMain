package com.example.salonmain;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginForm extends AppCompatActivity {

    private EditText edtxtEmail, edtxtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            edtxtEmail = findViewById(R.id.edTxtEmailLogin);
            edtxtPassword = findViewById(R.id.edtxtPassswordLogin);
            btnLogin = findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = String.valueOf(edtxtEmail.getText().toString());
                    String password = String.valueOf(edtxtPassword.getText().toString());
                    
                    if(TextUtils.isEmpty(email)){
                        Toast.makeText(loginForm.this, "Email is empty", Toast.LENGTH_SHORT).show();
                    }
                    if(TextUtils.isEmpty(password)){
                        Toast.makeText(loginForm.this, "Password is empty", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    }
}
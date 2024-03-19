package com.example.salonmain;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerEmail extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    Button btnRegister;

    FirebaseAuth mAuth;

    //   dapitoncaesar99@gmail.com


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_email);
        mAuth = FirebaseAuth.getInstance();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;});


        inputEmail =findViewById(R.id.getEmail);
        inputPassword =findViewById(R.id.getPass);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(inputEmail.getText().toString());
                String password = String.valueOf(inputPassword.getText().toString());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(registerEmail.this, "Email is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(registerEmail.this, "Password is empty", Toast.LENGTH_SHORT).show();
                }else {

                    createUserAccount(email, password);

                }
            }

        });
    }
    void createUserAccount(String email, String password){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign up success, update UI accordingly
                        FirebaseUser user = mAuth.getCurrentUser();
                        // Proceed to next activity or perform other tasks
                        Toast.makeText(this, "Account Success.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign up fails, display a message to the user.
                        Toast.makeText(this, "Failed to Create.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
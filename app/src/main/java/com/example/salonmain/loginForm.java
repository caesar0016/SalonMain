package com.example.salonmain;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class loginForm extends AppCompatActivity {

    private EditText edtxtEmail, edtxtPassword;
    Button btnLogin, btnLogout;
    private TextView tvRegisterAccount;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_form);
        mAuth = FirebaseAuth.getInstance();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            edtxtEmail = findViewById(R.id.edTxtEmailLogin);
            edtxtPassword = findViewById(R.id.edtxtPassswordLogin);
            btnLogin = findViewById(R.id.btnLogin);
            tvRegisterAccount = findViewById(R.id.tvRegisterUser);
            btnLogout = findViewById(R.id.btnLogout);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(loginForm.this, "Logout Success", Toast.LENGTH_SHORT).show();
                }
            });
            tvRegisterAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(loginForm.this, registerEmail.class);
                    startActivity(intent);
                }
            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = String.valueOf(edtxtEmail.getText().toString());
                    String password = String.valueOf(edtxtPassword.getText().toString());
                    
                    if(TextUtils.isEmpty(email)){
                        Toast.makeText(loginForm.this, "Email is empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(password)){
                        Toast.makeText(loginForm.this, "Password is empty", Toast.LENGTH_SHORT).show();
                    }else{
                        verifyLogin(email, password);
                    }
                }
            });


    }

    void verifyLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginForm.this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI accordingly
                        FirebaseUser user = mAuth.getCurrentUser();
                        // Proceed to next activity or perform other tasks
                        Toast.makeText(loginForm.this, "Authentication Success.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign in fails, handle the exception
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid credentials (e.g., wrong password)
                            Toast.makeText(loginForm.this, "Invalid email or password.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Other errors
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(loginForm.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
// dapitoncaesar26@gmail.com
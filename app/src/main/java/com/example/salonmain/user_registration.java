package com.example.salonmain;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_registration extends AppCompatActivity {

    private EditText edName, edUsername, edEmail, edPassword, edConfirmPass;
    private Spinner spinnerUserType;
    private Button btnSave;

    private DatabaseReference refUserAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edName = findViewById(R.id.edRegisterName);
        edUsername = findViewById(R.id.edRegisterUsername);
        edEmail = findViewById(R.id.edRegisterEmail);
        edPassword = findViewById(R.id.edRegisterPass);
        edConfirmPass = findViewById(R.id.edRegisterConfirmPass);
        btnSave = findViewById(R.id.btnAccountRegister);
        spinnerUserType = findViewById(R.id.spinnerUserType);

        refUserAccount = FirebaseDatabase.getInstance().getReference().child("Test");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAccountData();
            }
        });

    }

    void insertAccountData(){
        String name = edName.getText().toString();
        String username = edUsername.getText().toString();
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        String Confirmpassword = edConfirmPass.getText().toString();
        String userType = spinnerUserType.getSelectedItem().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please Fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        refUserAccount = FirebaseDatabase.getInstance().getReference().child("Test");

        userAccount_db inputAccount = new userAccount_db(name, username, email, password, userType);

        refUserAccount.push().setValue(inputAccount).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(user_registration.this, "Success Data Insert", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(user_registration.this, loginForm.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(user_registration.this, "Failed to insert Data", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
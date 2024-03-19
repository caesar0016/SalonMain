package com.example.salonmain;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class resgisterService extends AppCompatActivity {

    EditText edtxtServiceName, edtxtDesc, edtxtDuration, edtxtPrice;
    Button btnSave;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resgister_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;});

        edtxtServiceName = findViewById(R.id.edtxtServiceName);
        edtxtDesc = findViewById(R.id.edtxtDescription);
        edtxtDuration = findViewById(R.id.edtxtDuration);
        edtxtPrice = findViewById(R.id.edtxtPrice);

        btnSave = findViewById(R.id.btnSaveService);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Services");

                String service = edtxtServiceName.getText().toString();
                String description = edtxtDesc.getText().toString();
                String duration = edtxtDuration.getText().toString();
                double price = Double.parseDouble(edtxtPrice.getText().toString());

                service_data DataService = new service_data(service, description, duration, price);//insert data

                reference.child(service).setValue(DataService);
                Toast.makeText(resgisterService.this, "Insert Data Success", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
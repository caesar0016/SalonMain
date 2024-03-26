package com.example.salonmain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class registerService extends AppCompatActivity {

    EditText edtxtServiceName, edtxtDesc, edtxtDuration, edtxtPrice;
    Button btnSave, btnUpload;
    //FirebaseDatabase database;
    DatabaseReference serviceDBRef;
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView ProfilePic;
    private StorageReference storageRef;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resgister_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;});

        //this is the find by View ID
        edtxtServiceName = findViewById(R.id.edtxtServiceName);
        edtxtDesc = findViewById(R.id.edtxtDescription);
        edtxtDuration = findViewById(R.id.edtxtDuration);
        edtxtPrice = findViewById(R.id.edtxtPrice);
        btnSave = findViewById(R.id.btnSaveService);
        btnUpload = findViewById(R.id.btnUpload);
        storageRef = FirebaseStorage.getInstance().getReference();
        ProfilePic = findViewById(R.id.ProfilePic);

        storageRef = FirebaseStorage.getInstance().getReference();



        //this is the setOnClickListener
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImage();


            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceDBRef = FirebaseDatabase.getInstance().getReference().child("Services");

                String service = edtxtServiceName.getText().toString();
                String description = edtxtDesc.getText().toString();
                String duration = edtxtDuration.getText().toString();
                double price = Double.parseDouble(edtxtPrice.getText().toString());

                service_db inputService = new service_db(service, description, duration, price);//insert data

                serviceDBRef.push().setValue(inputService);
                Toast.makeText(registerService.this, "Insert Data Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ChooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            selectedImageUri = data.getData();
            ProfilePic.setImageURI(selectedImageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        if (selectedImageUri != null) {
            // Use selectedImageUri to upload the image
            // Create a reference to the location in Firebase Storage where you want to upload the image
            StorageReference imageRef = storageRef.child("images/" + UUID.randomUUID().toString()); // Generate a unique filename

            // Upload the file to Firebase Storage
            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Handle successful upload
                            // You can get the download URL of the uploaded image here if needed
                            // Uri downloadUri = taskSnapshot.getDownloadUrl();
                            Toast.makeText(registerService.this, "Upload successful", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle failed upload
                            Toast.makeText(registerService.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
}
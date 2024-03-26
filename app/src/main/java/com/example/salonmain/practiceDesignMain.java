package com.example.salonmain;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class practiceDesignMain extends AppCompatActivity {

    private RecyclerView rvRetrieve, rvStaff;
    private TextView tvService, tvDesc, tvDuration, tvPrice;
    private DatabaseReference reference, staffReference;
    private List<service_db> listOfService;
    private List<userAccount_db> ListOfUser;
    private serviceAdapter adapter;
    private staff_adapter adapterStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practice_design_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Service
        rvRetrieve = findViewById(R.id.rvService);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvRetrieve.setLayoutManager(layoutManager);
        listOfService = new ArrayList<>();
        adapter = new serviceAdapter(this, listOfService);
        rvRetrieve.setAdapter(adapter);
        reference = FirebaseDatabase.getInstance().getReference("Services");

        //Staff/Stylist
        rvStaff = findViewById(R.id.rvStaff1);
        LinearLayoutManager layoutManagerStaff = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvStaff.setLayoutManager(layoutManagerStaff);
        ListOfUser = new ArrayList<>();
        adapterStaff = new staff_adapter(this, ListOfUser);
        rvStaff.setAdapter(adapterStaff);
        staffReference = FirebaseDatabase.getInstance().getReference("Test");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOfService.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    service_db service = dataSnapshot.getValue(service_db.class);
                    listOfService.add(service);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(practiceDesignMain.this, "Error Retrieving Data", Toast.LENGTH_SHORT).show();
            }
        });

        staffReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ListOfUser.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    userAccount_db userDb = dataSnapshot.getValue(userAccount_db.class);
                    ListOfUser.add(userDb);

                }
                    adapterStaff.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(practiceDesignMain.this, "Error Retrieving Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
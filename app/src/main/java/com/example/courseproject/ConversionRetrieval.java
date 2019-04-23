package com.example.courseproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConversionRetrieval extends AppCompatActivity {

    Button retrieveButton;
    Button clearButton;

    private DatabaseReference appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_retrieval);

        retrieveButton = (Button) findViewById(R.id.retrieveButton);
        clearButton = (Button) findViewById(R.id.saveButton);

        retrieveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                appDatabase = FirebaseDatabase.getInstance().getReference();
                appDatabase.child("SavedConversions").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write .child("value") after .getReference() if necessary
                appDatabase = FirebaseDatabase.getInstance().getReference();
                appDatabase.removeValue();
            }
        });
    }
}

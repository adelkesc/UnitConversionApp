package com.example.courseproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConversionRetrieval extends AppCompatActivity {

    private static final String TAG = "PostActivityDetails";
    private DatabaseReference appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_retrieval);

        Button retrieveButton = (Button) findViewById(R.id.retrieveButton);
        Button clearButton = (Button) findViewById(R.id.saveButton);

        appDatabase = FirebaseDatabase.getInstance().getReference();

        retrieveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                appDatabase.child("SavedConversions").addListenerForSingleValueEvent(
                        new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int savedValue = dataSnapshot.child("SavedConversions")
                                .getValue(Integer.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });

//        clearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                appDatabase.removeValue();
//            }
//        });
    }
}

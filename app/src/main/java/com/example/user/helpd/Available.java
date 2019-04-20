package com.example.user.helpd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Available extends AppCompatActivity implements AvailableAdapter.Callback {

    private DatabaseReference databaseReference;
    private ListView listView;
    RecyclerView recyclerView;
    Helper helped;
    String contactKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);
       // final ArrayList<Helper> chosenHelper= new ArrayList<>();
        final ArrayList<Helper> chosenHelper= new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final AvailableAdapter adapter= new AvailableAdapter(Available.this);
        //AvailableAdapter adapter= new AvailableAdapter(chosenHelper);
        recyclerView.setAdapter(adapter);
        System.out.println("adapter set");

        /**** receiving data from main activity ****/
        Bundle firstData= getIntent().getExtras();
        Bundle secondData= getIntent().getExtras();
        Bundle thirdData= getIntent().getExtras();

        final String preference= firstData.getString("preference");
        final int timings= secondData.getInt("timings");
        contactKey= thirdData.getString("contactKey");


        databaseReference = FirebaseDatabase.getInstance().getReference("helper");
        System.out.println("got reference");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("ondatachange");
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    System.out.println("in for");
                    helped = ds.getValue(Helper.class);
                    if(preference.equals(helped.getServices()) && helped.getTimings()>=timings-1 && helped.getTimings() <= timings+2) {
                        chosenHelper.add(helped);
                        System.out.println("made a match");
                    }
                    /**** populating recycler view ****/
                //    AvailableAdapter adapter= new AvailableAdapter(Available.this);
                    //AvailableAdapter adapter= new AvailableAdapter(chosenHelper);
                    //recyclerView.setAdapter(adapter);
                    //System.out.println("adapter set");

                    adapter.setData(chosenHelper);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void friendCallback(Helper helper){
        Intent intent= new Intent(this, ClickHelperDetails.class);
        intent.putExtra("id", helper.getId());
        intent.putExtra("contactKey", contactKey);
        startActivity(intent);
    }
}

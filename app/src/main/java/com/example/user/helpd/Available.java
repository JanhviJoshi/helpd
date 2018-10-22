package com.example.user.helpd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Available extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView text;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);
       // final ArrayList<helper> chosenHelper= new ArrayList<>();
        final ArrayList<String> chosenHelper= new ArrayList<>();

        text= (TextView)findViewById(R.id.texttt);
        listView= (ListView)findViewById(R.id.listview);


        /**** receiving data from main activity ****/
        Bundle firstData= getIntent().getExtras();
        Bundle secondData= getIntent().getExtras();

        final String preference= firstData.getString("preference");
        final int timings= secondData.getInt("timings");


        databaseReference = FirebaseDatabase.getInstance().getReference("helper");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot help: dataSnapshot.getChildren()){
                    helper helped = help.getValue(helper.class);
                    if(preference.equals(helped.getServices()) && helped.getTimings()>=timings-1 && helped.getTimings() <= timings+2)
                        chosenHelper.add(helped.getName());
                }

                /**** populating list view ****/
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Available.this, R.layout.list_item, chosenHelper);

                listView.setAdapter(adapter);
                //text.setText(chosenHelper.get(0).getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

package com.example.user.helpd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClickHelperDetails extends AppCompatActivity {

    private static final String TAG = "Click helper";
    String contactKey;
    Helper mhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_helper_details);

        final TextView name= findViewById(R.id.textViewName);
        final TextView serv= findViewById(R.id.textViewServices);
        final TextView time= findViewById(R.id.textViewTime);
        Button button= findViewById(R.id.button);

        /** receiving ID from Available.java **/
        Bundle data= getIntent().getExtras();
        Bundle data2= getIntent().getExtras();
        final int id= data.getInt("id");
        Log.i(TAG, "HelperId: "+ id+" ");
        contactKey= data2.getString("contactKey");

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("helper").child(String.valueOf(id));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    mhelper = dataSnapshot.getValue(Helper.class);
                    if (id == mhelper.getId()) {
                        name.setText(mhelper.getName());
                        serv.setText(mhelper.getServices());
                        time.setText(String.valueOf(mhelper.getTimings()));
                   // }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   // confirm();
               // mhelper.setUserId(contactKey);
                FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                mhelper.setUserId(String.valueOf(user));

                String id= String.valueOf(mhelper.getId());

                DatabaseReference dr= FirebaseDatabase.getInstance().getReference("helper");
                dr.child(id).setValue(mhelper);
                //registered!
            }
        });

    }

    public void confirm(){

    }
}

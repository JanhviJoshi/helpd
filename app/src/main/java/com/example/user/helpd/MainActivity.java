package com.example.user.helpd;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioButton rb_clean;
    private RadioButton rb_wash;
    private RadioButton rb_cook;
    private RadioButton rb_morn;
    private RadioButton rb_eve;
    private Button button;
    private Button login;
    private DatabaseReference databaseReference;
    String code, time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /***** applying toolbar as the app bar  and setting the hamburger icon *****/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        /*************************************************************************/

        radioGroup1= (RadioGroup)findViewById(R.id.rad_help_pref);
        radioGroup2= (RadioGroup)findViewById(R.id.rad_time_pref);
        rb_clean= (RadioButton) findViewById(R.id.clean);
        rb_cook= (RadioButton)findViewById(R.id.cook);
        rb_wash= (RadioButton)findViewById(R.id.wash);
        rb_eve=(RadioButton)findViewById(R.id.eve);
        rb_morn=(RadioButton)findViewById(R.id.morn);
        button= (Button)findViewById(R.id.button);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        login= (Button)findViewById(R.id.login);
        NavigationView navigationView= (NavigationView)findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                //enter code for what to do when item selected
                //use if else here according to item selected

                return true;
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference= FirebaseDatabase.getInstance().getReference();
                userDetails user1= new userDetails();
                //pass the details of user to database keeping pref as null
                //intent for login page
                Intent l=new Intent(getBaseContext(),login_page.class);
                startActivity(l);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                /*** Getting help preference ***/
                databaseReference = FirebaseDatabase.getInstance().getReference();
                int id1= radioGroup1.getCheckedRadioButtonId();

                switch(id1)
                {
                    case R.id.cook: code= "cook";
                                    break;
                    case R.id.clean: code="clean";
                                    break;
                    case R.id.wash: code= "wash";
                                    break;
                }

                int id2= radioGroup2.getCheckedRadioButtonId();
                switch(id2)
                {
                    case R.id.morn: time= "morn";
                        break;
                    case R.id.eve: time="eve";
                        break;

                }

                UserPref user2= new UserPref(code, time);
                databaseReference.child("preference").setValue(user2);
                //when passing after login details, copy user1 ke name and all in user2

                Intent i= new Intent(MainActivity.this, Available.class);
                i.putExtra("preference",user2.getHelpPref());
                i.putExtra("timings", user2.getTimings());
                startActivity(i);



            }
        });




    }

    /***** Functionality for what to do when menu icon selected *****/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START); //opens drawer
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

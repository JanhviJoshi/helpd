package com.example.user.helpd;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;

public class MainActivity extends AppCompatActivity {



    private DrawerLayout drawerLayout;
    private EditText username, contact, address, city, email;
    private Button login, detect;
    private DatabaseReference databaseReference;

    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;

    double latitude, longitude;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Places.initialize(getApplicationContext(), "AIzaSyCmfK32HN7Ovg6YsSoOIISc_8Z9z96ftHY");
//        PlacesClient placesClient = Places.createClient(this);

        /***** applying toolbar as the app bar  and setting the hamburger icon *****/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        /***************************************************************************/


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        username = (EditText) findViewById(R.id.username);
        contact = (EditText) findViewById(R.id.contact);
       // address = (EditText) findViewById(R.id.address);
       // city = (EditText) findViewById(R.id.city);
        login = (Button) findViewById(R.id.login);
        email= (EditText) findViewById(R.id.email);
        detect=(Button)findViewById(R.id.loc);

        final UserDetails userDetails = new UserDetails();

        /******    Initiating the Places API Client *******/
        mGeoDataClient = Places.getGeoDataClient(this, null);
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        /*************************************************************************************************/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                //enter code for what to do when item in nav drawer selected
                //use if else here according to item selected

                return true;
            }
        });

        detect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getLocationPermission();
                //userDetails.setLatitude(latitude);
                //userDetails.setLongitude(longitude);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference();
                String n, c, add, cy, em;
                n = username.getText().toString();
                c = contact.getText().toString();
                em= email.getText().toString();

                //getting current location
                //getLocationPermission();


                //userDetails.setName(n);
                userDetails.setNumber(c);
                userDetails.setEmail(em);
                userDetails.setLatitude(latitude);
                userDetails.setLongitude(longitude);
                //UserDetails.setLatitude(latitude);
                //UserDetails.setLongitude(longitude);


               // UserDetails UserDetails = new UserDetails(n, c, em, latitude, longitude);  //not sending the preferences rn
                databaseReference.child("user").child(c).setValue(userDetails); //sending the login details to database


                Intent i = new Intent(MainActivity.this, Preferences.class);
                i.putExtra("contactKey", c);
                startActivity(i);
            }
        });
    }

    /***** Functionality for what to do when menu icon selected *****/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START); //opens drawer
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /************************************* GETTING CURRENT LOCATION **********************************************/
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */

        //method checkSelfPermission() is called to check if we already have the needed permission.
        //method returns PERMISSION_GRANTED if the app has the permission.
        //Otherwise it returns PERMISSION_DENIED, after which we ask the user for permission
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            getDeviceLocation();
        } else {

            //Android has many ways of asking for permission, the method requestPermissions() being one.
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    //callback
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        //mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    getDeviceLocation();
                }
            }
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            mLastKnownLocation = (Location)task.getResult();
                            latitude= mLastKnownLocation.getLatitude();
                            longitude= mLastKnownLocation.getLongitude();

                        }
                    }
               });
            }
        }
            catch(SecurityException e){
                Log.e("Exception: %s", e.getMessage());
            }

    }

    /*********************************************************************************************************/
}
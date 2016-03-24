package com.example.asatia.watersavers;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asatia.watersavers.SurveyPackage.FamilySizeFragment;
import com.example.asatia.watersavers.SurveyPackage.SurveyActivity;
import com.example.asatia.watersavers.SurveyPackage.Task;
import com.facebook.FacebookActivity;
import com.facebook.FacebookSdk;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks{

    private static int PLAY_SERVICES_RESOLUTION_REQUEST=1000;
    private Location myLocation;
    private GoogleApiClient mGoogleClient;
    ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(new LoginFragment());
        getLocation();
        spinner=(ProgressBar)findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);
        this.setProgressBarIndeterminateVisibility(false);
    }

    //Code for fetching location
    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleClient != null)
            mGoogleClient.connect();
        unsetVisible();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
        unsetVisible();
    }

    protected void displayLocation()   {
        myLocation=LocationServices.FusedLocationApi.getLastLocation(mGoogleClient);
        if(myLocation!=null)    {
            Log.e("My Location ","Lat "+myLocation.getLatitude()+" Lon "+myLocation.getLongitude());
        }
        else    {
            Log.e("Locaon ","No loc");
        }
    }

    public boolean checkPlayServices()  {
        int resultCode= GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.getApplicationContext());
        if(resultCode!=ConnectionResult.SUCCESS)    {
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode))   {
                GooglePlayServicesUtil.getErrorDialog(resultCode,this,PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"This device is not supported",Toast.LENGTH_SHORT).show();
                Log.e("google services", "This device is not supported");
                finish();
            }
            return false;
        }
        return true;
    }

    protected synchronized void buildGoogleApiClient()  {
        mGoogleClient=new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public void getLocation()   {
        if(checkPlayServices()) {
            Log.e("After check services","");
            buildGoogleApiClient();
            Log.e("After build client","");
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(),connectionResult.getErrorCode(),Toast.LENGTH_SHORT).show();
        Log.e("In connection failed ", "Connection failed :"
                + connectionResult.getErrorCode());
    }

    //Other methods for the activity
    public void hideToolbar()   {
        this.getSupportActionBar().hide();
    }

    public void showToolbarWithText(String text) {
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.baseColor)));
        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle(text);
    }

    public void changeFragment(Fragment fragment)   {
        this.getFragmentManager().beginTransaction()
                .replace(R.id.mainActivityContainer, fragment)
                .commit();
    }

    public void setVisible()    {
        spinner.setVisibility(View.VISIBLE);
    }
    public void unsetVisible()  {
        spinner.setVisibility(View.GONE);
    }

    public void changeActivity(String user,String first, String last, String lat,String lon)    {
        spinner.setVisibility(View.VISIBLE);
        Intent intent=new Intent(this.getApplicationContext(), SurveyActivity.class);
        intent.putExtra("username",user);
        intent.putExtra("first_name",first);
        intent.putExtra("last_name",last);
        intent.putExtra("latitude", lat);
        intent.putExtra("longitude", lon);
        startActivity(intent);
    }


    public void checkCredentials(String username,String password)  {
        try {
            this.setProgressBarIndeterminateVisibility(true);
            spinner.setVisibility(View.VISIBLE);
            String message = new Task().execute("login", username, password).get();
            Log.e("start of credentials ",message);
            JSONObject jsonObject=new JSONObject(message);
            if(jsonObject.getBoolean("status"))    {
                String arr[]=new String[5];
                changeActivity(
                jsonObject.getString("username"),
                jsonObject.getString("first_name"),
                jsonObject.getString("last_name"),
                jsonObject.getString("latitude"),
                jsonObject.getString("longitude")
                );
            }
            else    {
                Toast.makeText(getApplicationContext(),"Invalid credentials. Please try again",Toast.LENGTH_LONG).show();
                unsetVisible();
                return;
            }
        } catch (Exception e)   {
            Log.e("in check credentiasl ",e.toString());
            unsetVisible();
        }
    }

    public void registerUser(String first,String last,String user,String pass,String email)  {
        if(myLocation==null)  {
            Toast.makeText(getApplicationContext(),"Location not available. Could not register",Toast.LENGTH_LONG).show();
            unsetVisible();
        }
        else    {
            String lat= String.valueOf(myLocation.getLatitude());
            String lon=String.valueOf(myLocation.getLongitude());
            try {
                String response = new Task().execute("register", first, last, user, pass, email, lat, lon).get();
                JSONObject jsonObject=new JSONObject(response);
                if(jsonObject.getBoolean("status")) {
                    Toast.makeText(getApplication(), "Registration successful. Please login", Toast.LENGTH_LONG).show();
                    unsetVisible();
                    changeFragment(new LoginFragment());
                }
                else {
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    unsetVisible();
                }
            }
            catch (Exception e) {
                Log.e("in do register",e.toString());
                unsetVisible();
            }
        }
    }

    //Code for options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.back) {
            changeFragment(new LoginFragment());
        }
        return super.onOptionsItemSelected(item);
    }

}

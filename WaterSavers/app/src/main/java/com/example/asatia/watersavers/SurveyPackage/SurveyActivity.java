package com.example.asatia.watersavers.SurveyPackage;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asatia.watersavers.MainActivity;
import com.example.asatia.watersavers.R;
import com.example.asatia.watersavers.StartSurveyFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SurveyActivity extends AppCompatActivity {

    ListView listView;
    DrawerLayout drawerLayout;
    FacebookSdk facebookSdk;
    FacebookActivity facebookActivity;
    CallbackManager callbackManager;
    LoginManager loginManager;
    WaterData waterData;
    Menu menu;
    String hint="";
    ActionBarDrawerToggle toggle;
    ProgressBar spinner;
    public final static String TWIT_KEY = "70Z3rpOV10gAwEiuKOPrxUqzL";
    public final static String TWIT_SECRET = "zATnPVSzDEKYgbd6M7AbQjnEpvTPQiTpOFj6ptRnuiI8cgMZZx";
    public final static String TWIT_URL = "tnice-android:///";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        spinner=(ProgressBar)findViewById(R.id.surveySpinner);
        spinner.setVisibility(View.GONE);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.baseColor)));
       // setupFacebook();
        waterData=new WaterData();
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)    {
            waterData.setUserName(bundle.getString("username"));
            waterData.setFirstName(bundle.getString("first_name"));
            waterData.setLastName(bundle.getString("last_name"));
            waterData.setLatitude(Double.parseDouble(bundle.getString("latitude")));
            waterData.setLongitude(Double.parseDouble(bundle.getString("longitude")));
        }
        changeFragment(new StartSurveyFragment());
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        );
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
        String greetings="Hi "+this.waterData.getFirstName()+" "+this.waterData.getLastName();
        listView=(ListView)findViewById(R.id.list);
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,new String[]{greetings,"Tips","Map me","Leader Board","Goals"});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        changeFragment(new TipsFragment());
                        drawerLayout.closeDrawers();
                        break;
                    case 2:
                        changeFragment(new MapFragment());
                        drawerLayout.closeDrawers();
                        break;
                    case 3:
                        changeFragment(new LeaderBoardFragment());
                        drawerLayout.closeDrawers();
                        break;
                    case 4:
                        changeFragment(new GoalFragment());
                        drawerLayout.closeDrawers();
                        break;
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.no_star);

    }

    protected void onPostCreate(Bundle savedInstances)  {
        super.onPostCreate(savedInstances);
        toggle.syncState();
    }

    public void onConfigurationChanged(Configuration config)    {
        super.onConfigurationChanged(config);
        toggle.onConfigurationChanged(config);
    }

    public void changeFragment(Fragment frag)   {
        progressBarVisible();
        this.getFragmentManager().beginTransaction().replace(R.id.surveyActivityContainer, frag).commit();
        progressBarHide();
    }
    public void changeToolbarText(String text) {
        this.getSupportActionBar().setTitle(text);
    }

    public void setupFacebook() {
        Log.e("in setup facebook","adaada");
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        List<String> permission= Arrays.asList("publish_actions");
        loginManager=LoginManager.getInstance();
        loginManager.logInWithPublishPermissions(this,permission);
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("Here", "adaada");
                post();
            }

            @Override
            public void onCancel() {
                Log.e("cancelled", "adaada");

            }

            @Override
            public void onError(FacebookException e) {
                Log.e("Facebook Login", e.toString());
            }
        });
    }

    public void post()  {
        Log.e("in post","adaada");
        //Bitmap image= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ShareLinkContent content=new ShareLinkContent.Builder()
                .build();
        /*SharePhoto photo=new SharePhoto.Builder()
                .setBitmap(image)
                .setCaption("Water Savers")
                .build();*/
        /*SharePhotoContent content=new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();*/
        ShareApi.share(content, null);
        Log.e("conten shared", "adaada");

       /* ShareDialog shareDialog=new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
        if (ShareDialog.canShow(ShareLinkContent.class))    {
            SharePhotoContent content=new SharePhotoContent.Builder()
                    .setContentUrl(Uri.parse("Hi"))
                    .addPhoto(photo)
                    .build();
            shareDialog.show(content);
            ShareContent shareContent=new ShareFeedContent.Builder().setRef("Hi").build();
            ShareApi api=new ShareApi(content);
            api.setMessage("Hi");
            shareDialog.show(shareContent);
        }*/

    }

    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        callbackManager.onActivityResult(requestCode, responseCode, data);
    }
    public void calculateWaterconsumption() {
        ArrayList<String> tips=new ArrayList<>();
        ArrayList<String>stars=new ArrayList<>();
        double consumption,avg_consumption,water_saved,new_score=0;
        double shower,final_score=0,toilet,washing_machine,dishwasher;
        double shower_consumption_low = (waterData.getShowerFrequency() * waterData.getShowerDuration() * 1.6 * waterData.getFamilySize() * 30);
        double shower_consumption_high = (waterData.getShowerFrequency() * waterData.getShowerDuration() * 3 * waterData.getFamilySize() * 30);
        double shower_consumption_avg = (2 * 8 * 2 * waterData.getFamilySize() * 30);
        double toilet_consumption_low = (waterData.getFlushFrequency() * 1.8 * waterData.getFamilySize() * 30);
        double toilet_consumption_high = (waterData.getFlushFrequency() * 3 * waterData.getFamilySize() * 30);
        double toilet_consumption_avg = (5 * 2 * waterData.getFamilySize() * 30);

        float washingmachine_consumption_low = (waterData.getWashingMachineFrequency() * 25 * 4);

        float washingmachine_consumption_high = (waterData.getWashingMachineFrequency() * 40 * 4);

        float washingmachine_consumption_avg = (30 * 30);

        float dishwasher_consumption_low = (waterData.getDishWasherFrequency() * 10 * 30);

        float dishwasher_consumption_high = (waterData.getDishWasherFrequency() * 18 * 30);

        float dishwasher_consumption_avg = (2 * 12 * 30);

        float sprinkler_consumption = (waterData.getSprinklerNumber() * waterData.getSprinklerDuration() * 7 * 8);

        float sprinkler_consumption_avg = (waterData.getSprinklerDuration() * 12 * 6 * 8);

        float misc_consumption = (waterData.getMiscellaneous() * waterData.getFamilySize() * 30);

        float misc_consumption_avg = (45 * waterData.getFamilySize() * 30);

        if (waterData.getShowerHeadType()==1) {
            shower = shower_consumption_low;
        }
        else
            shower = shower_consumption_high;

        if(shower_consumption_avg > shower){
            final_score = final_score + (shower_consumption_avg - shower);
        }
        else
        {
            tips.add("Take shorter showers.");
            tips.add("Replace your shower head with a ultra low-flow version.");
        }

        if (waterData.getFlushType()==1) {
            toilet = toilet_consumption_low;
        }
        else
            toilet = toilet_consumption_high;

        if(toilet_consumption_avg > toilet){
            final_score = final_score + (toilet_consumption_avg - toilet);
        }
        else
        {
            tips.add("Replace your toilet flush with dual or pressure assist flush.");
            tips.add("Don't flush things down the toilet to dispose of them.");
        }


        if (waterData.getWashingMachineType()==1) {
            washing_machine = washingmachine_consumption_low;
        }
        else
        washing_machine = washingmachine_consumption_high;

        if(washingmachine_consumption_avg > washing_machine){
            final_score = final_score + (washingmachine_consumption_avg - washing_machine);
        }
        else
        {
            tips.add("Do full loads or select appropriate water level settings.");
        }
        if (waterData.getDishWasherType()==1) {
            dishwasher = dishwasher_consumption_low;
        }
        else
            dishwasher = dishwasher_consumption_high;

        if(dishwasher_consumption_avg > dishwasher){
            final_score = final_score + (dishwasher_consumption_avg - dishwasher);
        }
        else
        {
            tips.add("Operate dishwasher only when they are fully loaded.");
            tips.add("Avoid pre-rinsing of dishes.");
        }

        if(sprinkler_consumption_avg > sprinkler_consumption)
        {
            final_score = final_score + (sprinkler_consumption_avg - sprinkler_consumption);
        }
        else
        {
            tips.add("Water early in the morning to reduce the evaporation rate.");
            tips.add("Make sure you set automatic sprinklers correctly that adjust as conditions change.");
        }


        if(misc_consumption_avg > misc_consumption){
            final_score = final_score + (misc_consumption_avg - misc_consumption);
        }
        else
        {
            tips.add("Install low flow faucet aerators in your sink.");
            tips.add("Check water bills for any instance of high water use as this may be an indication of leak.");
        }

        consumption = shower + toilet + washing_machine + dishwasher + sprinkler_consumption + misc_consumption;
        waterData.setConsumption((long)consumption);
        avg_consumption = shower_consumption_avg + toilet_consumption_avg + washingmachine_consumption_avg + dishwasher_consumption_avg + sprinkler_consumption_avg + misc_consumption_avg;

        water_saved = avg_consumption - consumption;
        water_saved=Math.round(water_saved);
        waterData.setWaterSaved((long)water_saved);
        if(water_saved > 0)
            new_score = water_saved * 3;
        waterData.setTip(tips);
        waterData.setScore((long)new_score);
        waterData.setUsage((long)water_saved);
        int numberOfStars= (int) Math.round(waterData.getScore()/1250);
        while((numberOfStars/4)>=1) {
            stars.add("full_star");
            numberOfStars=numberOfStars-4;
        }
        switch (numberOfStars)
        {
            case 1:
                stars.add("quarter_star");
                break;
            case 2:
                stars.add("half_star");
                break;
            case 3:
                stars.add("threeFourth_star");
                break;
        }
        for(int i=stars.size();i<5;i++) {
            stars.add("no_star");
        }
        waterData.setStars(stars);
    }

    public void submitScore()   {
        try {
            String flush_type,dishwasher_type,shower_type,washing_type;
            if(waterData.getFlushType()==1)
                flush_type="YES";
            else
                flush_type="NO";
            if(waterData.getDishWasherType()==1)
                dishwasher_type="YES";
            else
                dishwasher_type="NO";
            if(waterData.getShowerHeadType()==1)
                shower_type="YES";
            else
                shower_type="NO";
            if(waterData.getWashingMachineType()==1)
                washing_type="Front Load";
            else
                washing_type="Top_Load";
            JSONObject data=new JSONObject();
            data.put("username",waterData.getUserName());
            data.put("water_use",waterData.getConsumption());
            data.put("user_score",waterData.getScore());
            data.put("badge_status","0");
            data.put("family_size",waterData.getFamilySize());
            data.put("dishwasher_frequency",waterData.getDishWasherFrequency());
            data.put("sprinkler_frequency",waterData.getSprinklerNumber());
            data.put("sprinkler_operation_time",waterData.getSprinklerDuration());
            data.put("low_flow_showerhead",shower_type);
            data.put("shower_length",waterData.getShowerDuration());
            data.put("shower_frequency",waterData.getShowerFrequency());
            data.put("flush_type",flush_type);
            data.put("flush_frequency",waterData.getFlushFrequency());
            data.put("machine_type",washing_type);
            data.put("machine_loads",waterData.getWashingMachineFrequency());
            data.put("dishwasher_type",dishwasher_type);
            data.put("misc",waterData.getMiscellaneous());
            String response = new Task()
                    .execute("submit",data.toString())
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void progressBarVisible()    {
        spinner.setVisibility(View.VISIBLE);
    }

    public void progressBarHide()   {
        spinner.setVisibility(View.GONE);
    }

    public ArrayList<LeaderBoardListItem> getLeaderBoard()  {
        ArrayList<LeaderBoardListItem> list=new ArrayList<>();
        try {
            String content = new Task().execute("leaderboard").get();
            Log.e("after callin api",content);
            JSONObject object=new JSONObject(content);
            if(!object.getBoolean("error")) {
                JSONArray array=object.getJSONArray("result");
                Log.e("size of array ",array.length()+"");
                for(int i=0;i<array.length();i++)   {
                    LeaderBoardListItem item=new LeaderBoardListItem();
                    JSONObject jsonObject=array.getJSONObject(i);
                    Log.e("for ",i+" "+jsonObject.getString("first_name"));
                    item.setName(jsonObject.getString("first_name") + " " + jsonObject.getString("last_name"));
                    item.setSerialNo((i + 1) + ".");
                    item.setScore(jsonObject.getInt("user_score") + "");
                    Log.e("for ", i + " " + jsonObject.getString("first_name") + " " + jsonObject.getInt("badge_status"));
                    item.setBadge(jsonObject.getInt("badge_status"));
                    list.add(item);
                }
            }
            return list;
        }


        catch (Exception e)  {
            Log.e("Exeception leaderboard",e.toString());
        }
        return null;
    }

    public ArrayList<MapListItem> getMapItem()  {
        ArrayList<MapListItem> list=new ArrayList<>();
        try {
            String content = new Task().execute("leaderboard").get();
            Log.e("after callin api",content);
            JSONObject object=new JSONObject(content);
            if(!object.getBoolean("error")) {
                JSONArray array=object.getJSONArray("result");
                Log.e("size of array ",array.length()+"");
                for(int i=0;i<array.length();i++)   {
                    MapListItem item=new MapListItem();
                    JSONObject jsonObject=array.getJSONObject(i);
                    Log.e("for ",i+" "+jsonObject.getString("first_name"));
                    item.setUser(jsonObject.getString("first_name") + " " + jsonObject.getString("last_name"));
                    item.setScore(jsonObject.getInt("user_score"));
                    Log.e("for ", i + " " + jsonObject.getString("first_name") + " " + jsonObject.getInt("badge_status"));
                    item.setLat(jsonObject.getDouble("latitude"));
                    item.setLon(jsonObject.getDouble("longitude"));
                    if(waterData.getUserName().equals(jsonObject.getString("username")))    {
                        item.setUser(true);
                    }
                    else {
                        item.setUser(false);
                    }

                    list.add(item);
                }
            }
            return list;
        }


        catch (Exception e)  {
            Log.e("Exeception Map",e.toString());
        }
        return null;
    }

    public void facebookPost()  {
        Log.e("facebook post","adaada");
        setupFacebook();
    }

    public void showHint(String text)  {
        this.menu.findItem(R.id.hints).setVisible(true);
        this.hint=text;
    }

    public void hideHint()  {
        this.menu.findItem(R.id.hints).setVisible(false);
    }

    public void displayHint()   {
        AlertDialog.Builder hint=new AlertDialog.Builder(this);
        hint.setMessage(this.hint);
        hint.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog=hint.create();
        alertDialog.show();
    }

    public void changeActivity()    {
        Intent intent=new Intent(this.getApplicationContext(), MainActivity.class);
        Toast.makeText(this, "You have succcessfully Logged out", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu=menu;
        getMenuInflater().inflate(R.menu.menu_survey, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(toggle.onOptionsItemSelected(item))
            return true;
        if (id == R.id.action_settings) {
            changeActivity();
        }

        if(id==R.id.hints)  {
            displayHint();
        }

        return super.onOptionsItemSelected(item);
    }
}

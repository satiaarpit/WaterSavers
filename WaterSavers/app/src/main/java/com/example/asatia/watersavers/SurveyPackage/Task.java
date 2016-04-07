package com.example.asatia.watersavers.SurveyPackage;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asatia on 11/28/2015.
 */
public class Task extends AsyncTask <String,Void,String>{
    @Override
    protected String doInBackground(String... params) {
        switch (params[0])   {
            case "login":
               return doLogin(params[1],params[2]);
            case "register":
                return doRegister(params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
            case "leaderboard":
                return getLeaderBoard();
            case "submit":
                return doSubmit(params[1]);
        }
        return "";
    }
    public String doLogin(String username,String password)   {
        String result="";
        InputStreamReader in = null;
        BufferedReader reader=null;
        try {
            URL url = new URL("http://watersavers.iosnewbies.com/v2/login/"+username+"/"+password);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new InputStreamReader(urlConnection.getInputStream());
            reader=new BufferedReader(in);
            String content="";
            String line;
            while((line=reader.readLine())!=null)  {
                content+=line;
            }
            Log.e("Content :",content);
            return content;
        } catch (Exception e ) {
            Log.e("Exception ", e.toString());
        }
        return "";

    }

    public String doRegister(String first,String last,String user,String pass,String email,String lat,String lon)   {
        InputStreamReader in = null;
        BufferedReader reader=null;
        try {
            URL url = new URL("http://watersavers.iosnewbies.com/v2/register/"+user+"/"+email+"/"+pass+"/"+first+"/"+last+"/"+lat+"/"+lon);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new InputStreamReader(urlConnection.getInputStream());
            reader=new BufferedReader(in);
            String content="";
            String line;
            while((line=reader.readLine())!=null)  {
                content+=line;
            }
            Log.e("Content leaderboard :",content);
            return content;
        } catch (Exception e ) {
            Log.e("Exception in register ", e.toString());
        }
        return "";
    }

    public String getLeaderBoard()  {
        InputStreamReader in = null;
        BufferedReader reader=null;
        try {
            URL url = new URL("http://watersavers.iosnewbies.com/v2/leaderboard");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new InputStreamReader(urlConnection.getInputStream());
            reader=new BufferedReader(in);
            String content="";
            String line;
            while((line=reader.readLine())!=null)  {
                content+=line;
            }
            return content;
        } catch (Exception e ) {
            Log.e("Exception ", e.toString());
        }
        return "";
    }

    public String doSubmit(String dataContent)    {
        String result="";
        InputStreamReader in = null;
        BufferedReader reader=null;
        try {
            byte [] postData=dataContent.getBytes();
            URL url = new URL("http://watersavers.iosnewbies.com/v2/storescoredetails");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.getOutputStream().write(postData);
            in = new InputStreamReader(urlConnection.getInputStream());
            reader=new BufferedReader(in);
            String content="";
            String line;
            while((line=reader.readLine())!=null)  {
                content+=line;
            }
            return content;
        } catch (Exception e ) {
            Log.e("Exception ", e.toString());
        }
        return "";
    }
}


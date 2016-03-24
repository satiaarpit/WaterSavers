package com.example.asatia.watersavers;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.devspark.robototextview.widget.RobotoButton;
import com.devspark.robototextview.widget.RobotoEditText;
import com.devspark.robototextview.widget.RobotoTextView;
import com.example.asatia.watersavers.SurveyPackage.Task;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    MainActivity activity;
    RobotoButton loginButton;
    RobotoButton register;
    RobotoEditText username,password;
   // ProgressBar spinner;
    public LoginFragment() {
        // Required empty public constructor
    }

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(MainActivity)a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_login, container, false);
        activity.hideToolbar();
        //spinner=(ProgressBar)rootView.findViewById(R.id.spinner1);
        //spinner.setVisibility(View.GONE);
        username=(RobotoEditText)rootView.findViewById(R.id.registeredUserName);
        password=(RobotoEditText)rootView.findViewById(R.id.registeredPassword);
        loginButton=(RobotoButton)rootView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setVisible();
                String user=String.valueOf(username.getText());
                String pass=String.valueOf(password.getText());
                if (user==null)
                    Toast.makeText(activity.getApplicationContext(),"Please enter a valid user name",Toast.LENGTH_SHORT).show();
                else if (pass==null)
                    Toast.makeText(activity.getApplicationContext(),"Please enter a valid password",Toast.LENGTH_SHORT).show();
                else {
                    Log.e("In login ", "Username " + user + " pass " + pass);
                    activity.getLocation();
                    activity.checkCredentials(user, pass);
                }
            }
        });
        register=(RobotoButton)rootView.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new SignUpFragment());
            }
        });
        return rootView;
    }


}

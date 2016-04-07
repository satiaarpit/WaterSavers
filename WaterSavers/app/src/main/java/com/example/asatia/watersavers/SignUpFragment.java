package com.example.asatia.watersavers;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devspark.robototextview.widget.RobotoButton;
import com.devspark.robototextview.widget.RobotoEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    MainActivity activity;
    RobotoEditText firstName,lastName,userName,password,confirmPassword,email;
    RobotoButton signUpButton;
    String error="";
    public SignUpFragment() {
        // Required empty public constructor
    }

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(MainActivity)a;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_sign_up, container, false);
        firstName=(RobotoEditText)rootView.findViewById(R.id.firstName);
        lastName=(RobotoEditText)rootView.findViewById(R.id.lastName);
        userName=(RobotoEditText)rootView.findViewById(R.id.userName);
        password=(RobotoEditText)rootView.findViewById(R.id.password);
        confirmPassword=(RobotoEditText)rootView.findViewById(R.id.confirmPassword);
        email=(RobotoEditText)rootView.findViewById(R.id.email);
        signUpButton=(RobotoButton)rootView.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setVisible();
                if (checkFields()) {
                    activity.registerUser(String.valueOf(firstName.getText()), String.valueOf(lastName.getText()),String.valueOf(userName.getText()),String.valueOf(password.getText()), String.valueOf(email.getText()));
                }
                else {
                    Toast.makeText(activity.getApplicationContext(),error,Toast.LENGTH_LONG).show();
                    activity.unsetVisible();
                }
            }
        });
        activity.showToolbarWithText("Sign Up");
        return rootView;
    }

    public boolean checkFields()    {
        String first=String.valueOf(firstName.getText());
        String last=String.valueOf(lastName.getText());
        String user=String.valueOf(userName.getText());
        String pass=String.valueOf(password.getText());
        String confirm=String.valueOf(confirmPassword.getText());
        String emailId=String.valueOf(email.getText());
        if(first.equals("")||first.equals(null))   {
            error="Please provide a valid first name";
            return false;
        }
        else    {
            if (last.equals("")||last.equals(null))  {
                error="Please provide a valid last name";
                return false;
            }else   {
                if(user.equals("")||user.equals(null))   {
                    error="Please provide a valid user name";
                    return false;
                }
                else {
                    if(emailId.equals("")||emailId.equals(null)||!android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches())  {
                        error="Please provide a valid email";
                        return false;
                    }
                    else    {
                        if(pass.equals("")||pass.equals(null))   {
                            error="Please provide a valid password";
                            return false;
                        }
                        else {
                            if (!confirm.equals(pass))  {
                                error="Password does not match";
                                return false;
                            }
                            else {
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }


}

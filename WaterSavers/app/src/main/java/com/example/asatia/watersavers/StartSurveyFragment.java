package com.example.asatia.watersavers;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import com.devspark.robototextview.widget.RobotoButton;
import com.example.asatia.watersavers.SurveyPackage.FamilySizeFragment;
import com.example.asatia.watersavers.SurveyPackage.SurveyActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartSurveyFragment extends Fragment {


    SurveyActivity activity;
    RobotoButton startSurvey;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_start_survey, container, false);
        activity.changeToolbarText("Start Survey");
        //activity.hideHint();
        startSurvey=(RobotoButton)rootView.findViewById(R.id.startSurveyButton);
        startSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new FamilySizeFragment());
            }
        });
        return rootView;
    }
}
    
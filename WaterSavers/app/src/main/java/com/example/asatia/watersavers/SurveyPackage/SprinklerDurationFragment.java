package com.example.asatia.watersavers.SurveyPackage;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.example.asatia.watersavers.MainActivity;
import com.example.asatia.watersavers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SprinklerDurationFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker sprinklerDuration;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public SprinklerDurationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_sprinkler_duration, container, false);
        activity.changeToolbarText("12");
        activity.showHint(getString(R.string.sprinkler_duration_hint));
        sprinklerDuration=(NumberPicker)rootView.findViewById(R.id.sprinklerDuration);
        sprinklerDuration.setMaxValue(20);
        sprinklerDuration.setMinValue(0);
        sprinklerDuration.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.sprinklerDurationLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new SprinklerNumberFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.sprinklerDurationRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(sprinklerDuration.getValue()));
                activity.waterData.setSprinklerDuration(sprinklerDuration.getValue());
                activity.changeFragment(new MiscellaneousFragment());
            }
        });
        return rootView;
    }
}

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
public class SprinklerNumberFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker sprinklerNumber;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public SprinklerNumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_sprinkler_number, container, false);
        activity.changeToolbarText("11");
        activity.hideHint();
        sprinklerNumber=(NumberPicker)rootView.findViewById(R.id.sprinklerNumber);
        sprinklerNumber.setMaxValue(5);
        sprinklerNumber.setMinValue(0);
        sprinklerNumber.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.sprinklerNumberLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new DishWasherFrequencyFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.sprinklerNumberRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(sprinklerNumber.getValue()));
                activity.waterData.setSprinklerNumber(sprinklerNumber.getValue());
                activity.changeFragment(new SprinklerDurationFragment());
            }
        });
        return rootView;
    }


}

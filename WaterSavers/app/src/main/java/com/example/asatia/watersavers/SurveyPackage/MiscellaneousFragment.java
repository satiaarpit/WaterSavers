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
public class MiscellaneousFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker miscellaneous;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public MiscellaneousFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_miscellaneous, container, false);
        activity.changeToolbarText("13");
        activity.showHint(getString(R.string.misc_hint));
        miscellaneous = (NumberPicker) rootView.findViewById(R.id.miscellaneous);
        miscellaneous.setMaxValue(12);
        miscellaneous.setMinValue(1);
        miscellaneous.setDisplayedValues(new String[]{"5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60"});
        miscellaneous.setWrapSelectorWheel(false);
        back = (ImageView) rootView.findViewById(R.id.miscellaneousLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new SprinklerDurationFragment());
            }
        });
        forward = (ImageView) rootView.findViewById(R.id.miscellaneousRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(miscellaneous.getValue()));
                activity.waterData.setMiscellaneous(miscellaneous.getValue() * 5);
                activity.calculateWaterconsumption();
                activity.changeFragment(new ResultFragment());
            }
        });
        return rootView;
    }
}

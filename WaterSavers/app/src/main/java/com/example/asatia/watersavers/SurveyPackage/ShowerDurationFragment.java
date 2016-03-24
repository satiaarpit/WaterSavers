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
public class ShowerDurationFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker showerDuration;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public ShowerDurationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_shower_duration, container, false);
        activity.changeToolbarText("3");
        activity.showHint(getString(R.string.shower_duration_hint));
        showerDuration=(NumberPicker)rootView.findViewById(R.id.showerDuration);
        showerDuration.setMaxValue(35);
        showerDuration.setMinValue(5);
        showerDuration.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.showerDurationLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    activity.changeFragment(new ShowerHeadFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.showerDurationRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(showerDuration.getValue()));
                activity.waterData.setShowerDuration(showerDuration.getValue());
                activity.changeFragment(new ShowerFrequencyFragment());
            }
        });
        return rootView;
    }

}

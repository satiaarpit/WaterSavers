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
public class ShowerFrequencyFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker showerFrequency;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public ShowerFrequencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_shower_frequency, container, false);
        activity.changeToolbarText("4");
        activity.hideHint();
        showerFrequency=(NumberPicker)rootView.findViewById(R.id.showerFrequency);
        showerFrequency.setMaxValue(5);
        showerFrequency.setMinValue(1);
        showerFrequency.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.showerFrequencyLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new ShowerDurationFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.showerFrequencyRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(showerFrequency.getValue()));
                activity.waterData.setShowerFrequency(showerFrequency.getValue());
                activity.changeFragment(new FlushTypeFragment());
            }
        });
        return rootView;
    }


}

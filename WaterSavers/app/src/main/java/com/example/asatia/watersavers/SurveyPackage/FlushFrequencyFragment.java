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
public class FlushFrequencyFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker flushFrequency;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public FlushFrequencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_flush_frequency, container, false);
        activity.changeToolbarText("6");
        activity.showHint(getString(R.string.flush_duration_hint));
        flushFrequency=(NumberPicker)rootView.findViewById(R.id.flushFrequency);
        flushFrequency.setMaxValue(10);
        flushFrequency.setMinValue(1);
        flushFrequency.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.flushFrequencyLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new FlushTypeFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.flushFrequencyRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(flushFrequency.getValue()));
                activity.waterData.setFlushFrequency(flushFrequency.getValue());
                activity.changeFragment(new WashingMachineTypeFragment());
            }
        });
        return rootView;
    }


}

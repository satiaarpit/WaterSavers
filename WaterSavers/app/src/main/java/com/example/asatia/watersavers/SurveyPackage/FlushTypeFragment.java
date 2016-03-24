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
public class FlushTypeFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker flushType;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public FlushTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_flush_type, container, false);
        activity.changeToolbarText("5");
        activity.showHint(getString(R.string.flush_type_hint));
        flushType=(NumberPicker)rootView.findViewById(R.id.flushType);
        flushType.setMaxValue(1);
        flushType.setMinValue(0);
        flushType.setDisplayedValues(new String[]{"No", "Yes"});
        flushType.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.flushTypeLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new ShowerFrequencyFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.flushTypeRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(flushType.getValue()));
                activity.waterData.setFlushType(flushType.getValue());
                activity.changeFragment(new FlushFrequencyFragment());
            }
        });
        return rootView;
    }


}

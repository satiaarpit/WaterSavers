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
public class WashingMachineTypeFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker washingMachineType;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public WashingMachineTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_washing_machine_type, container, false);
        activity.changeToolbarText("7");
        activity.hideHint();
        washingMachineType=(NumberPicker)rootView.findViewById(R.id.washingMachineType);
        washingMachineType.setMaxValue(1);
        washingMachineType.setMinValue(0);
        washingMachineType.setDisplayedValues(new String[]{"Top Load", "Front Load"});
        washingMachineType.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.washingMachineTypeLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new FlushFrequencyFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.washingMachineTypeRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(washingMachineType.getValue()));
                activity.waterData.setWashingMachineType(washingMachineType.getValue());
                activity.changeFragment(new WashingMachineFrequencyFragment());
            }
        });
        return rootView;
    }

}

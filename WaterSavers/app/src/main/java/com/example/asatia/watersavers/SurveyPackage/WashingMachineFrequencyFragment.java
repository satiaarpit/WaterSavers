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
public class WashingMachineFrequencyFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker washingMachineFrequency;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public WashingMachineFrequencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_washing_machine_frequency, container, false);
        activity.changeToolbarText("8");
        activity.hideHint();
        washingMachineFrequency=(NumberPicker)rootView.findViewById(R.id.washingMachineFrequency);
        washingMachineFrequency.setMaxValue(10);
        washingMachineFrequency.setMinValue(0);
        washingMachineFrequency.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.washingMachineFrequencyLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new WashingMachineTypeFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.washingMachineFrequencyRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(washingMachineFrequency.getValue()));
                activity.waterData.setFamilySize(washingMachineFrequency.getValue());
                activity.changeFragment(new DishWasherTypeFragment());
            }
        });
        return rootView;
    }

}

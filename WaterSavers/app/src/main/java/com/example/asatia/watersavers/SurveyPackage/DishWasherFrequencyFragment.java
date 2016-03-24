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

public class DishWasherFrequencyFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker dishWasherFrequency ;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public DishWasherFrequencyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_dish_washer_frequency, container, false);
        activity.changeToolbarText("10");
        activity.hideHint();
        dishWasherFrequency=(NumberPicker)rootView.findViewById(R.id.dishWasherFrequency);
        dishWasherFrequency.setMaxValue(10);
        dishWasherFrequency.setMinValue(0);
        dishWasherFrequency.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.dishWasherFrequencyLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new DishWasherTypeFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.dishWasherFrequencyRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(dishWasherFrequency.getValue()));
                activity.waterData.setDishWasherFrequency(dishWasherFrequency.getValue());
                activity.changeFragment(new SprinklerNumberFragment());
            }
        });
        return rootView;
    }

}

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

public class DishWasherTypeFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker dishWasherType ;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }
    public DishWasherTypeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.fragment_dish_washer_type, container, false);
        activity.changeToolbarText("9");
        activity.showHint(getString(R.string.dishwasher_type_hint));
        dishWasherType=(NumberPicker)rootView.findViewById(R.id.dishWasherType);
        dishWasherType.setMaxValue(1);
        dishWasherType.setMinValue(0);
        dishWasherType.setDisplayedValues(new String[]{"No","Yes"});
        dishWasherType.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.dishWasherTypeLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new WashingMachineFrequencyFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.dishWasherTypeRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(dishWasherType.getValue()));
                activity.waterData.setDishWasherType(dishWasherType.getValue());
                activity.changeFragment(new DishWasherFrequencyFragment());
            }
        });
        return rootView;
    }


}

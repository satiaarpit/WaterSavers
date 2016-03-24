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
public class ShowerHeadFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker showerHead;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }

    public ShowerHeadFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_shower_head, container, false);
        activity.changeToolbarText("2");
        activity.showHint(getString(R.string.shower_head_type_hint));
        showerHead=(NumberPicker)rootView.findViewById(R.id.showerHeadType);
        showerHead.setMaxValue(1);
        showerHead.setMinValue(0);
        showerHead.setDisplayedValues(new String[]{"No", "Yes"});
        showerHead.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.showerHeadLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(new FamilySizeFragment());
            }
        });
        forward=(ImageView)rootView.findViewById(R.id.showerHeadRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(showerHead.getValue()));
                activity.waterData.setFamilySize(showerHead.getValue());
                activity.changeFragment(new ShowerDurationFragment());
            }
        });
        return rootView;
    }

}

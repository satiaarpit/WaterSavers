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
public class FamilySizeFragment extends Fragment {

    SurveyActivity activity;
    NumberPicker familySize ;
    ImageView back,forward;

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }

    public FamilySizeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_family_size, container, false);
        activity.changeToolbarText("1");
        activity.hideHint();
        familySize=(NumberPicker)rootView.findViewById(R.id.familySize);
        familySize.setMaxValue(8);
        familySize.setMinValue(1);
        familySize.setWrapSelectorWheel(false);
        back=(ImageView)rootView.findViewById(R.id.familySizeLeft);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        forward=(ImageView)rootView.findViewById(R.id.familySizeRight);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Value : ", String.valueOf(familySize.getValue()));
                activity.waterData.setFamilySize(familySize.getValue());
                activity.changeFragment(new ShowerHeadFragment());
            }
        });
        return rootView;
    }

}

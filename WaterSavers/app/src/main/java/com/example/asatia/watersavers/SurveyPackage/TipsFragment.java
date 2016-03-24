package com.example.asatia.watersavers.SurveyPackage;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asatia.watersavers.R;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class TipsFragment extends Fragment {

    SurveyActivity activity;
    ListView tips;
    TipListAdapter adapter;
    public TipsFragment() {
        // Required empty public constructor
    }

    public void onAttach(Activity  a)   {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_tips, container, false);
        activity.progressBarVisible();
        activity.changeToolbarText("Tips");
        activity.hideHint();
        tips=(ListView)rootview.findViewById(R.id.tipList);
        adapter=new TipListAdapter(activity.getApplicationContext(),R.layout.tips_list_item,activity.waterData.getTip());
        tips.setAdapter(adapter);
        activity.progressBarHide();
        return rootview;
    }


}

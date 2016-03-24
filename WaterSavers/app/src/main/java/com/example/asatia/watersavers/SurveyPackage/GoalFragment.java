package com.example.asatia.watersavers.SurveyPackage;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.asatia.watersavers.R;

import junit.framework.Assert;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoalFragment extends Fragment {

    ListView goals;
    SurveyActivity activity;
    GoalListAdapter adapter;
    ArrayList<GoalListItem> arrayList;
    public GoalFragment() {
        // Required empty public constructor
    }

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_goal, container, false);
        activity.hideHint();
        activity.changeToolbarText("My goals");
        goals=(ListView)rootView.findViewById(R.id.goalList);
        arrayList=new ArrayList<GoalListItem>();
        populateList();
        adapter=new GoalListAdapter(activity.getApplicationContext(),R.layout.goal_list_item,arrayList);
        goals.setAdapter(adapter);
        return rootView;
    }

    public void populateList()  {
        GoalListItem item[]=new GoalListItem[3];
        item[0]=new GoalListItem();
        item[0].setMedal(R.drawable.bronze_medal);
        item[0].setMilestone("Milestone 1");
        item[0].setMinScore("25000");
        item[0].setCoupons(new int[]{R.drawable.dominos,R.drawable.mcd,R.drawable.starbucks,R.drawable.br});
        item[1]=new GoalListItem();
        item[1].setMedal(R.drawable.silver_medal);
        item[1].setMilestone("Milestone 2");
        item[1].setMinScore("50000");
        item[1].setCoupons(new int[]{R.drawable.nike,R.drawable.adidas,R.drawable.walmart,R.drawable.target});
        item[2]=new GoalListItem();
        item[2].setMedal(R.drawable.gold_medal);
        item[2].setMilestone("Milestone 3");
        item[2].setMinScore("100000");
        item[2].setCoupons(new int[]{R.drawable.amazon,R.drawable.apple,R.drawable.hp,R.drawable.windows});
        if(activity.waterData.getScore()>100000)    {
            item[0].setLock(false);
            item[1].setLock(false);
            item[2].setLock(false);
        }   else    {
            if(activity.waterData.getScore()>50000)    {
                item[0].setLock(false);
                item[1].setLock(false);
                item[2].setLock(true);
            }   else    {
                if(activity.waterData.getScore()>25000)    {
                    item[0].setLock(false);
                    item[1].setLock(true);
                    item[2].setLock(true);
                }   else    {
                        item[0].setLock(true);
                        item[1].setLock(true);
                        item[2].setLock(true);
                }
            }
        }
        arrayList.add(item[0]);
        arrayList.add(item[1]);
        arrayList.add(item[2]);
    }
}

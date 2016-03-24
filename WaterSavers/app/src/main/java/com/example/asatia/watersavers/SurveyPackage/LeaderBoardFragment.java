package com.example.asatia.watersavers.SurveyPackage;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asatia.watersavers.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderBoardFragment extends Fragment {

    SurveyActivity activity;
    ArrayList<LeaderBoardListItem> arrayList;
    ListView leaderBoardList;
    LeaderBoardListAdapter adapter;
    public LeaderBoardFragment() {

    }

    public void onAttach(Activity a)  {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_leader_board, container, false);
        activity.changeToolbarText("Leader Board");
        activity.hideHint();
        leaderBoardList=(ListView)rootView.findViewById(R.id.leaderBoardList);
        arrayList=new ArrayList<LeaderBoardListItem>();
        arrayList=activity.getLeaderBoard();
        adapter=  new LeaderBoardListAdapter(activity.getApplicationContext(),R.layout.leaderboard_list_item,arrayList);
        leaderBoardList.setAdapter(adapter);
        return rootView;
    }
}

package com.example.asatia.watersavers.SurveyPackage;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.asatia.watersavers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    SurveyActivity activity;
    ImageView star_one,star_two,star_three,star_four,star_five;
    ImageView facebook;
    RobotoTextView score,conclusion;
    public ResultFragment() {
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
        View rootView= inflater.inflate(R.layout.fragment_result, container, false);
        activity.submitScore();
        activity.hideHint();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.changeToolbarText("Result");
        star_one=(ImageView)rootView.findViewById(R.id.star_one);
        star_two=(ImageView)rootView.findViewById(R.id.star_two);
        star_three=(ImageView)rootView.findViewById(R.id.star_three);
        star_four=(ImageView)rootView.findViewById(R.id.star_four);
        star_five=(ImageView)rootView.findViewById(R.id.star_five);
        assignStars();
        score=(RobotoTextView)rootView.findViewById(R.id.score);
        if(activity.waterData.getScore()!=0)
            score.setText(String.valueOf(activity.waterData.getScore()));
        else
            score.setText("0");
        conclusion=(RobotoTextView)rootView.findViewById(R.id.conclusion);
        if(activity.waterData.getWaterSaved()<0)
            conclusion.setText("You have used "+Math.abs(activity.waterData.getWaterSaved())+" gallons of water more than the average this month");
        else
            conclusion.setText("You have saved "+activity.waterData.getWaterSaved()+" gallons of water this month");
        facebook=(ImageView)rootView.findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.facebookPost();
            }
        });
        return rootView;
    }
    public void assignStars()   {
        switch(activity.waterData.getStars().get(0))    {
            case "full_star":
                star_one.setImageResource(R.drawable.star);
                break;
            case "threeFourth_star":
                star_one.setImageResource(R.drawable.three_star);
                break;
            case "half_star":
                star_one.setImageResource(R.drawable.half_star);
                break;
            case "quarter_star":
                star_one.setImageResource(R.drawable.quater_star);
                break;
            case "no_star":
                star_one.setImageResource(R.drawable.no_star);
                break;
        }
        switch(activity.waterData.getStars().get(1))    {
            case "full_star":
                star_two.setImageResource(R.drawable.star);
                break;
            case "threeFourth_star":
                star_two.setImageResource(R.drawable.three_star);
                break;
            case "half_star":
                star_two.setImageResource(R.drawable.half_star);
                break;
            case "quarter_star":
                star_two.setImageResource(R.drawable.quater_star);
                break;
            case "no_star":
                star_two.setImageResource(R.drawable.no_star);
                break;
        }
        switch(activity.waterData.getStars().get(2))    {
            case "full_star":
                star_three.setImageResource(R.drawable.star);
                break;
            case "threeFourth_star":
                star_three.setImageResource(R.drawable.three_star);
                break;
            case "half_star":
                star_three.setImageResource(R.drawable.half_star);
                break;
            case "quarter_star":
                star_three.setImageResource(R.drawable.quater_star);
                break;
            case "no_star":
                star_three.setImageResource(R.drawable.no_star);
                break;
        }
        switch(activity.waterData.getStars().get(3))    {
            case "full_star":
                star_four.setImageResource(R.drawable.star);
                break;
            case "threeFourth_star":
                star_four.setImageResource(R.drawable.three_star);
                break;
            case "half_star":
                star_four.setImageResource(R.drawable.half_star);
                break;
            case "quarter_star":
                star_four.setImageResource(R.drawable.quater_star);
                break;
            case "no_star":
                star_four.setImageResource(R.drawable.no_star);
                break;
        }
        switch(activity.waterData.getStars().get(4))    {
            case "full_star":
                star_five.setImageResource(R.drawable.star);
                break;
            case "threeFourth_star":
                star_five.setImageResource(R.drawable.three_star);
                break;
            case "half_star":
                star_five.setImageResource(R.drawable.half_star);
                break;
            case "quarter_star":
                star_five.setImageResource(R.drawable.quater_star);
                break;
            case "no_star":
                star_five.setImageResource(R.drawable.no_star);
                break;
        }
    }

}

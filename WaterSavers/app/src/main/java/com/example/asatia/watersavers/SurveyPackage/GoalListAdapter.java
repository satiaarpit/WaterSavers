package com.example.asatia.watersavers.SurveyPackage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asatia.watersavers.R;

import java.util.ArrayList;

/**
 * Created by asatia on 11/25/2015.
 */
public class GoalListAdapter extends ArrayAdapter<GoalListItem> {
    Context context;
    ArrayList<GoalListItem> arrayList;
    LayoutInflater inflater;
    int resource;

    public GoalListAdapter(Context context, int resource, ArrayList<GoalListItem> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.arrayList = arrayList;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(resource, null);
            viewHolder = new ViewHolder();
            viewHolder.milestone= (TextView) convertView.findViewById(R.id.milestone);
            viewHolder.minScore = (TextView) convertView.findViewById(R.id.minScore);
            viewHolder.medal = (ImageView) convertView.findViewById(R.id.medal);
            viewHolder.coupon_one = (ImageView) convertView.findViewById(R.id.coupon_onee);
            viewHolder.coupon_two = (ImageView) convertView.findViewById(R.id.coupon_two);
            viewHolder.coupon_three = (ImageView) convertView.findViewById(R.id.coupon_three);
            viewHolder.coupon_four = (ImageView) convertView.findViewById(R.id.coupon_four);
            viewHolder.lock = (ImageView) convertView.findViewById(R.id.lock);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GoalListItem goalList = arrayList.get(position);
        /*viewHolder.medal.setImageResource(android.R.color.transparent);
        viewHolder.coupon_one.setImageResource(android.R.color.transparent);
        viewHolder.coupon_two.setImageResource(android.R.color.transparent);
        viewHolder.coupon_three.setImageResource(android.R.color.transparent);
        viewHolder.coupon_four.setImageResource(android.R.color.transparent);*/
        viewHolder.medal.setImageResource(goalList.getMedal());
        viewHolder.milestone.setText(goalList.getMilestone());
        viewHolder.minScore.setText(goalList.getMinScore());
        if(goalList.getLock())  {
            viewHolder.lock.setImageResource(R.drawable.closed_lock);
        }
        else    {
            viewHolder.lock.setImageResource(R.drawable.open_lock);
        }
        Log.e("Here",goalList.getCoupons(1)+"");
       //viewHolder.coupon_one.setImageResource(goalList.getCoupons(0));
        viewHolder.coupon_two.setImageResource(goalList.getCoupons(1));
        viewHolder.coupon_three.setImageResource(goalList.getCoupons(2));
        viewHolder.coupon_four.setImageResource(goalList.getCoupons(3));
        return convertView;
    }


    class ViewHolder {
        public TextView milestone,minScore;
        public ImageView medal,lock,coupon_one,coupon_two,coupon_three,coupon_four;
    }
}

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


public class LeaderBoardListAdapter extends ArrayAdapter<LeaderBoardListItem> {
    Context context;
    ArrayList<LeaderBoardListItem> arrayList;
    LayoutInflater inflater;
    int resource;

    public LeaderBoardListAdapter(Context context, int resource, ArrayList<LeaderBoardListItem> arrayList) {
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
            viewHolder.serialNo = (TextView) convertView.findViewById(R.id.serialNo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.bronze = (ImageView) convertView.findViewById(R.id.bronzeMedal);
            viewHolder.silver = (ImageView) convertView.findViewById(R.id.silverMedal);
            viewHolder.gold = (ImageView) convertView.findViewById(R.id.goldMedal);
            viewHolder.score = (TextView) convertView.findViewById(R.id.score);
            convertView.setTag(viewHolder);
        } else {
           viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.gold.setImageResource(android.R.color.transparent);
        viewHolder.bronze.setImageResource(android.R.color.transparent);
        viewHolder.silver.setImageResource(android.R.color.transparent);
        LeaderBoardListItem list = arrayList.get(position);
        Log.e("In adapter", list.getName() + " " + list.getBadge() + " " + position);
        viewHolder.serialNo.setText(list.getSerialNo());
        viewHolder.name.setText(list.getName());
        viewHolder.score.setText(list.getScore());
            switch (list.getBadge()) {
                case 3:
                    viewHolder.gold.setImageResource(R.drawable.gold_medal);
                case 2:
                    viewHolder.silver.setImageResource(R.drawable.silver_medal);
                case 1:
                    viewHolder.bronze.setImageResource(R.drawable.bronze_medal);
                    break;
                case 0:
                    break;
        }
        return convertView;
    }

    private static class ViewHolder {
        public TextView serialNo, name,score;
        public ImageView bronze,silver,gold;
    }
}


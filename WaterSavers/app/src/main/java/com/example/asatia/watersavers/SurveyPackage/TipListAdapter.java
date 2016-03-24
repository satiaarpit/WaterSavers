package com.example.asatia.watersavers.SurveyPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asatia.watersavers.R;

import java.util.ArrayList;

/**
 * Created by asatia on 11/29/2015.
 */
public class TipListAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> arrayList;
    LayoutInflater inflater;
    int resource;

    public TipListAdapter(Context context, int resource, ArrayList<String> arrayList) {
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
            viewHolder.tip = (TextView) convertView.findViewById(R.id.tip_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(position%2==0)   {
            viewHolder.tip.setTextColor(context.getResources().getColor(R.color.black));
        }
        else    {
            viewHolder.tip.setTextColor(context.getResources().getColor(R.color.red));
        }
        String tip = arrayList.get(position);
        viewHolder.tip.setText(tip);
        return convertView;
    }

    class ViewHolder {
        public TextView tip;
    }
}

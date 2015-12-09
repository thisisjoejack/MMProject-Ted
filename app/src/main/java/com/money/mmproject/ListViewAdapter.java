package com.money.mmproject;

import static com.money.mmproject.Constants.FIRST_COLUMN;
import static com.money.mmproject.Constants.SECOND_COLUMN;
import static com.money.mmproject.Constants.THIRD_COLUMN;
import static com.money.mmproject.Constants.FOURTH_COLUMN;
import static com.money.mmproject.Constants.FIFTH_COLUMN;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nessa on 12/8/15.
 */
public class ListViewAdapter extends BaseAdapter{
    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;
    TextView txtFifth;

    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.column_row, null);

            txtFirst=(TextView) convertView.findViewById(R.id.keyId);
            txtSecond=(TextView) convertView.findViewById(R.id.date);
            txtThird=(TextView) convertView.findViewById(R.id.amount);
            txtFourth=(TextView) convertView.findViewById(R.id.category);
            txtFifth=(TextView) convertView.findViewById(R.id.description);

        }

        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get(FIRST_COLUMN));
        txtSecond.setText(map.get(SECOND_COLUMN));
        txtThird.setText(map.get(THIRD_COLUMN));
        txtFourth.setText(map.get(FOURTH_COLUMN));
        txtFifth.setText(map.get(FIFTH_COLUMN));

        return convertView;
    }
}

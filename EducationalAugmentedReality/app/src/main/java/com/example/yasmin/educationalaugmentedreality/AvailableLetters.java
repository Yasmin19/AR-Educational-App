package com.example.yasmin.educationalaugmentedreality;

/**
 *
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AvailableLetters extends BaseAdapter {
    private Context mContext;
    static TextView textView;

    public AvailableLetters(Context c) {mContext = c;}

    public int getCount() {
        return 12;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // First time attributes need to be set

            textView = new TextView(mContext);
            textView.setBackgroundColor(Color.parseColor("#2a0a52"));
            textView.setBackgroundResource(R.drawable.rounded_box_select);
            textView.setPadding(5, 5, 5, 5);
            textView.setLayoutParams(new GridView.LayoutParams(116, 125));
            textView.setTextSize(43);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setTypeface(CrossWordActivity.font);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(" "+String.valueOf(CameraActivity.letters[position]));
        return textView;
    }

}
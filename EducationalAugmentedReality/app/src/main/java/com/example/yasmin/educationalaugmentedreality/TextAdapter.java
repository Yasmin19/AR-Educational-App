package com.example.yasmin.educationalaugmentedreality;

/**
 * Created by Yasmin on 13/04/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TextAdapter extends BaseAdapter {
    private Context mContext;

    public TextAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return 100;
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
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes

            textView = new TextView(mContext);
            textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView.setBackgroundResource(R.drawable.rounded_box);
            textView.setPadding(5, 5, 5, 5);
            textView.setLayoutParams(new GridView.LayoutParams(85,85));
            textView.setText("Y");
        } else {
            // imageView = (ImageView) convertView;
            textView = (TextView) convertView;
        }

        // imageView.setImageResource(R.drawable.box);
        return textView;
    }

}
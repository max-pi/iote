package com.example.android.actionbarcompat.styled;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by maxp on 15-11-20.
 */
public class BeaconGridIconAdapter extends BaseAdapter {
    private Context mContext;

    public BeaconGridIconAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
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
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }


        for (int i = 0; i<mThumbIds.length; i++) {
            // example icons
            mThumbIds[i] = R.drawable.location_icon_white;
        }

        imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }

    Integer[] mThumbIds = new Integer[20];
}
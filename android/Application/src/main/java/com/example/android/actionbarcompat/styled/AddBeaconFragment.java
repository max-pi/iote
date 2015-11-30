package com.example.android.actionbarcompat.styled;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by maxp on 15-11-19.
 */
public class AddBeaconFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_addbeacon, container, false);

        //stops tabbing while in add beacon
        getActivity().getActionBar().hide();
        CustomViewPager viewPager = (CustomViewPager) getActivity().findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);


        GridView gridView = (GridView) rootView.findViewById(R.id.beaconIconGridView);

        gridView.setAdapter(new BeaconGridIconAdapter(getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {



            }
        });


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();


        // enables tabbing after leaving add beacon
        getActivity().getActionBar().show();
        CustomViewPager viewPager = (CustomViewPager) getActivity().findViewById(R.id.pager);
        viewPager.setPagingEnabled(true);

    }
}
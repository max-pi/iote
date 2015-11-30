package com.example.android.actionbarcompat.styled;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MyBeaconsFragment extends Fragment {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    ArrayAdapter<String> beaconsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mybeacons, container, false);

        ListView beaconsListView = (ListView) rootView.findViewById(R.id.mybeaconsListView);

        String[] items = {"hi","there","hal"};

        beaconsAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.beacon_listitem, items);

        beaconsListView.setAdapter(beaconsAdapter);

//        beaconsAdapter.add(new IoteTag("tagid", "userid", "name"));
//        beaconsAdapter.add(new IoteTag("tagid", "userid", "name"));
//        beaconsAdapter.add(new IoteTag("tagid", "userid", "name"));


        Button addBeaconButton = (Button) rootView.findViewById(R.id.addBeaconButton);

        addBeaconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // navigate to add beacon page
                Fragment frag = new AddBeaconFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();

                Log.d("navigation", "to add beacon");
            }
        });




        Button faqButton = (Button) rootView.findViewById(R.id.mybeaconsFaqButton);

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // navigate to faq page
                Fragment frag = new FaqFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();

                Log.d("navigation", "pressed faq!!!");
            }
        });



        return rootView;
    }
}

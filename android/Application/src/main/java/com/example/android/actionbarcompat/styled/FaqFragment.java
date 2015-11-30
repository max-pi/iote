package com.example.android.actionbarcompat.styled;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FaqFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_faq, container, false);

        // hides tabs
        getActivity().getActionBar().hide();

        //stops tabbing while in faq
        CustomViewPager viewPager = (CustomViewPager) getActivity().findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        getActivity().getActionBar().show();

        // enables tabbing after leaving faq
        CustomViewPager viewPager = (CustomViewPager) getActivity().findViewById(R.id.pager);
        viewPager.setPagingEnabled(true);

        Log.d("navigation","leaving faq");
    }
}
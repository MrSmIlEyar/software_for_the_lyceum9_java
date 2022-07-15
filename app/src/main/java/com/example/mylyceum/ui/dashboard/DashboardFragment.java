package com.example.mylyceum.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;


import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mylyceum.R;
import com.example.mylyceum.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    private DashboardAddBoard dashboard_add_board = new DashboardAddBoard();
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TabHost tabHost = binding.tabHost;

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        Date d = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E");

        String day_of_week = formatForDateNow.format(d);
        System.out.println(day_of_week);



        tabSpec.setContent(R.id.linearLayout);
        tabSpec.setIndicator("пн");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.linearLayout2);
        LinearLayout linearLayout = binding.tab2;
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        linearLayout.removeAllViews();
        linearLayout.addView(relativeLayout);
        tabSpec.setIndicator("вт");

        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.linearLayout3);
        tabSpec.setIndicator("ср");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag4");
        tabSpec.setContent(R.id.linearLayout4);
        tabSpec.setIndicator("чт");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag5");
        tabSpec.setContent(R.id.linearLayout5);
        tabSpec.setIndicator("пт");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag6");
        tabSpec.setContent(R.id.linearLayout6);
        tabSpec.setIndicator("сб");
        tabHost.addTab(tabSpec);

        switch (day_of_week) {
            case "Mon":
                tabHost.setCurrentTab(0);
                break;
            case "Tue":
                tabHost.setCurrentTab(1);
                break;
            case "Wed":
                tabHost.setCurrentTab(2);
                break;
            case "Thu":
                tabHost.setCurrentTab(3);
                break;
            case "Fri":
                tabHost.setCurrentTab(4);
                break;
            case "Sat":
                tabHost.setCurrentTab(5);
                break;
            default:
                tabHost.setCurrentTab(0);
                break;

        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


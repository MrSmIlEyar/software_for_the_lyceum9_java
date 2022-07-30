package com.example.mylyceum.ui.dashboard;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;


import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.mylyceum.R;
import com.example.mylyceum.databinding.FragmentDashboardBinding;

import java.util.Calendar;
import java.util.Date;


public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private AddBoard addBoard;
    private Activity activity;

    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

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


//        Date date = new Date();
//        int day_of_week = getDayNumberOld(date) - 1;
        tabSpec.setContent(R.id.linearLayout);
        tabSpec.setIndicator("пн");
        tabHost.addTab(tabSpec);



        LinearLayout linearLayout345 = binding.tab2;
        linearLayout345.removeAllViews();
        linearLayout345.addView(addDay("vt"));


        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.linearLayout2);
        LinearLayout linearLayout = binding.linearLayout2;
//        linearLayout.addView(dashboard_add_board.getRelativeLayout("вт"));
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

    private LinearLayout addDay(String day) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout relativeLayout = new LinearLayout(getContext());
        relativeLayout.setLayoutParams(params);
        for (int i = 0; i < 100; i++) {
            CardView cardView = new CardView(getContext());
            CardView.LayoutParams params_2 = new CardView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 150);
            cardView.setLayoutParams(params_2);
            cardView.setCardElevation(50);

            int red = (int) (Math.random() * 200);
            int blue = (int) (Math.random() * 200);
            int green = (int) (Math.random() * 200);
            cardView.setCardBackgroundColor(Color.rgb(red, green, blue));
            relativeLayout.addView(cardView);


        }

        return relativeLayout;

    }
}


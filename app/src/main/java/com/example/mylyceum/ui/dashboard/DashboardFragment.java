package com.example.mylyceum.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.mylyceum.R;
import com.example.mylyceum.databinding.FragmentDashboardBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private AddBoard addBoard;
    private Activity activity;
    private static final int CARD_SIZE = 150;

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
        FrameLayout tabcontent = binding.tabcontent;


//        Date date = new Date();
//        int day_of_week = getDayNumberOld(date) - 1;
        tabSpec.setContent(R.id.linearLayout);
        tabSpec.setIndicator("пн");
        tabHost.addTab(tabSpec);

        ScrollView scrollView = binding.vt;
        scrollView.addView(addDay("vt"));
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

        ViewTreeObserver vto = tabcontent.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                tabcontent.getViewTreeObserver().removeOnPreDrawListener(this);

                int finalHeight = tabcontent.getMeasuredHeight();
                finalHeight -= 154;
                ViewGroup.LayoutParams params = tabcontent.getLayoutParams();
                params.height = finalHeight;

                tabcontent.setLayoutParams(params);

                return true;
            }
        });


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
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 100; i++) {
            CardView cardView = new CardView(getContext());
            CardView.LayoutParams cardParams = new CardView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, toPixels(CARD_SIZE));
            cardParams.setMargins(toPixels(16), toPixels(16), toPixels(16), toPixels(16));
            cardView.setLayoutParams(cardParams);
            cardView.setCardElevation(50);
//            int red = (int) (Math.random() * 200);
//            int blue = (int) (Math.random() * 200);
//            int green = (int) (Math.random() * 200);
            cardView.setCardBackgroundColor(Color.WHITE);
            cardView.addView(generateCard());
            linearLayout.addView(cardView);


        }

        return linearLayout;
    }

    private RelativeLayout generateCard() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(params);

        TextView timeTextView = new TextView(getContext());
        ViewGroup.LayoutParams timeTextViewParams = new ViewGroup.LayoutParams(
                toPixels(CARD_SIZE), ViewGroup.LayoutParams.MATCH_PARENT);
        timeTextView.setLayoutParams(timeTextViewParams);
        timeTextView.setGravity(Gravity.CENTER);
        timeTextView.setTextSize(toPixels(9));
        timeTextView.setTextColor(Color.parseColor("#FFFFFFFF"));
        timeTextView.setBackgroundColor(Color.parseColor("#5A3D30"));
        timeTextView.setText("8.00\n-\n8.45");

        TextView upperTextView = new TextView(getContext());
        ViewGroup.LayoutParams upperTextViewParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewGroup.MarginLayoutParams upperTextViewMarginParams = new ViewGroup.MarginLayoutParams(upperTextViewParams);
        upperTextViewMarginParams.setMargins(toPixels(CARD_SIZE), 0, 0, toPixels((int) CARD_SIZE / 2));
        upperTextView.setLayoutParams(upperTextViewMarginParams);
        upperTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        upperTextView.setTextSize(toPixels(9));
        upperTextView.setTextColor(Color.BLACK);
        upperTextView.setPadding(0, toPixels(40), 0, 0);
        upperTextView.setText("Алгебра");

        TextView bottomTextView = new TextView(getContext());
        ViewGroup.LayoutParams bottomTextViewParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewGroup.MarginLayoutParams bottomTextViewMarginParams = new ViewGroup.MarginLayoutParams(bottomTextViewParams);
        bottomTextViewMarginParams.setMargins(toPixels(CARD_SIZE), toPixels((int) CARD_SIZE / 2), 0, 0);
        bottomTextView.setLayoutParams(bottomTextViewMarginParams);
        bottomTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        bottomTextView.setTextSize(toPixels(6));
        bottomTextView.setPadding(0, toPixels(10), 0, 0);
        bottomTextView.setText("Кабинет 303");

        relativeLayout.addView(bottomTextView);
        relativeLayout.addView(timeTextView);
        relativeLayout.addView(upperTextView);

        return relativeLayout;
    }

    private int toPixels(int dp) {
        Resources r = getContext().getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }
}


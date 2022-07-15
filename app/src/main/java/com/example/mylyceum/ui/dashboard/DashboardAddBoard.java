package com.example.mylyceum.ui.dashboard;

import android.app.ActionBar;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;


public class DashboardAddBoard {
    private String dayOfWeek;
    private RelativeLayout relativeLayout;
    private CardView cardView;

    public RelativeLayout getRelativeLayout(String dayOfWeek) {
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout
                        .LayoutParams
                        .MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        cardView.setBackgroundColor(Color.rgb(255, 255, 255));
        cardView.setContentPadding(16, 24,16,24);
        cardView.setMaxCardElevation(4);
        cardView.setElevation(2);
        cardView.setRadius(10);
        cardView.setLayoutParams(new ViewGroup.LayoutParams(
                CardView.LayoutParams.MATCH_PARENT, 150));
//        cardView.setForegroundGravity();
        return relativeLayout;
    }

}

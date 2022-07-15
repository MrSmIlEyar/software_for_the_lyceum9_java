package com.example.mylyceum.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.mylyceum.R;
import com.example.mylyceum.databinding.FragmentDashboardBinding;
import com.google.android.material.navigation.NavigationView;



public class DashboardAddBoard extends AppCompatActivity{
    private String dayOfWeek;
    private RelativeLayout relativeLayout;
    private CardView cardView;


    public RelativeLayout getRelativeLayout(String dayOfWeek) {
//        RelativeLayout relativeLayout = findViewById(R.id.relativers);

        System.out.println("True");


        return relativeLayout;
    }

}

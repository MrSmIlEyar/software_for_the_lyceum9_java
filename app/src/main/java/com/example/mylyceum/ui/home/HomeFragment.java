package com.example.mylyceum.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mylyceum.R;
import com.example.mylyceum.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private int chet;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        chet = 0;
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        LinearLayout linearLayout = binding.linearlayout;
//        Добавление карточек с новостями
        for (int i = 0; i < 100; i++) {
            TextView textView = new TextView(getContext());
            textView.setText("Hello World");
            linearLayout.addView(textView);
        }





        return root;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
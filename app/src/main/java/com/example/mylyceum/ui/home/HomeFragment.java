package com.example.mylyceum.ui.home;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
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


import java.util.ArrayList;

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
        ArrayList<String> news = new ArrayList<>();
        news.add("Я тут");
        news.add("а нет я \n тут");
        news.add("Я здеся");
//        Добавление карточек с новостями
        for (int i = 0; i < 100; i++) {
            TextView textView = new TextView(getContext());
            CardView card = new CardView(getContext());
            card.setCardElevation(50);
            int red = (int) (Math.random() * 200);
            int blue = (int) (Math.random() * 200);
            int green = (int) (Math.random() * 200);
            int index = (int) (Math.random() * 2);
            textView.setText(news.get(index));
            textView.setTextColor(Color.rgb(0, 0, 0));
            card.setCardBackgroundColor(Color.rgb(red, green, blue));
            card.addView(textView);
            linearLayout.addView(card);
        }

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}


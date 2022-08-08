package com.example.mylyceum.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
        news.add("Поздравляем всех с 1 сентября! Желаем, чтобы этот учебный год был не менее продуктивным, чем предыдущий, более насыщенным, ярким, уникальным!");
        news.add("а нет я \n тут \n\n\n\n\n");

//        Добавление карточек с новостями
        for (int i = 0; i < 100; i++) {
            TextView textView = new TextView(getContext());
            CardView card = new CardView(getContext());
            int index = (int) (Math.random() * 2);
            textView.setText(news.get(index));
            textView.setTextSize(20);
            card.addView(textView);
            card.setCardElevation(12);
            LinearLayout.LayoutParams card_news = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            card_news.setMargins(8,20,8,20);
            linearLayout.addView(card, card_news);
        }

        return root;

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

}


package com.example.mylyceum.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
            textView.setTextColor(Color.rgb(0, 0, 0));
            card.addView(textView);
            card.setCardElevation(12);
            LinearLayout.LayoutParams card_news = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            card_news.setMargins(5,50,5,50);
            linearLayout.addView(card, card_news);
        }
        ScrollView scrollView = binding.scrollView;

        ViewTreeObserver vto = scrollView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                scrollView.getViewTreeObserver().removeOnPreDrawListener(this);

                int finalHeight = scrollView.getMeasuredHeight();
                finalHeight -= 154;
                ViewGroup.LayoutParams params = scrollView.getLayoutParams();
                params.height = finalHeight;

                scrollView.setLayoutParams(params);

                return true;
            }
        });

        return root;

    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

}


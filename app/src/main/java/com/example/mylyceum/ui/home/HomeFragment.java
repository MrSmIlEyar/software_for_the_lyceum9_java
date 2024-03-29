package com.example.mylyceum.ui.home;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private DatabaseReference myRef;
    private ArrayList<String> news = new ArrayList<>();

    private FragmentHomeBinding binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ОНО ЖИВОЕ");
        myRef = FirebaseDatabase.getInstance().getReference();
//        for (int i = 0; i < 5; i++) {
////            myRef..setValue("Ярик почему не смержил");
//            myRef.child(i + "").setValue(i);
//        }

        myRef.getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.child("News").getChildren()) {
                    news.add(childDataSnapshot.getValue().toString());
                   Log.d("NewsGetting", childDataSnapshot.getKey() + " " + childDataSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        this.news = news;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        myRef = FirebaseDatabase.getInstance().getReference();
////        for (int i = 0; i < 5; i++) {
//////            myRef..setValue("Ярик почему не смержил");
////            myRef.child(i + "").setValue(i);
////        }
        LinearLayout linearLayout = binding.linearlayout;

//        Добавление карточек с новостями
        for (int i = 0; i < this.news.size(); i++) {
            TextView textView = new TextView(getContext());
            CardView card = new CardView(getContext());
            textView.setText(this.news.get(i));
            textView.setTypeface(Typeface.SANS_SERIF);
            textView.setTextSize(20);
            textView.setTextColor(Color.rgb(0, 0, 0));
            card.addView(textView);
            card.setCardElevation(50);
            LinearLayout.LayoutParams card_news = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            card_news.setMargins(0, 25, 0, 25);
            linearLayout.addView(card, card_news);
        }
        Log.d("CreateNewsCard", "Карточки с новостями созданы");
        Log.d("CreateNewsCard", this.news.size() + "");
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    
    
    
}


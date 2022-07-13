package com.example.mylyceum.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mylyceum.databinding.FragmentDashboardBinding;
import com.google.android.material.tabs.TabItem;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        TabItem monday = binding.monday;
//        TabItem tuesday = binding.tuesday;
//        TabItem wednesday = binding.wednesday;
//        TabItem thursday = binding.thursday;
//        TabItem friday = binding.friday;
//        TabItem saturday = binding.saturday;
//        ViewPager2 viewPager = binding.viewpagers;
//        PageAdapter pageAdapter = new PageAdapter(this);
//        viewPager.setAdapter(pageAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
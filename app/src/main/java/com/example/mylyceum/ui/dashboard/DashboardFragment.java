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
//        TabItem monday = binding.mondays;
//        TabItem tuesday = binding.tuesdays;
//        TabItem wednesday = binding.wednesdays;
//        TabItem thursday = binding.thursdays;
//        TabItem friday = binding.fridays;
//        TabItem saturday = binding.saturdays;
//        ViewPager2 viewPager = binding.viewpagers;
//        PageAdapter pageAdapter = new PageAdapter(getActivity());
//        viewPager.setAdapter(pageAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
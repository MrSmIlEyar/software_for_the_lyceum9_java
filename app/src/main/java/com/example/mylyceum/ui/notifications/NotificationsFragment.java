package com.example.mylyceum.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mylyceum.MainActivity;
import com.example.mylyceum.check;
import com.example.mylyceum.databinding.FragmentNotificationsBinding;
import com.example.mylyceum.main2;

import java.text.BreakIterator;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private BreakIterator textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button vk = binding.vknot;
        Button tg = binding.telegrem;
        Button exit = binding.accountExit;
        vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/pushkinisti"));
                startActivity(intent);
            }
        });
        tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.telegram.org/z/#-1731032322"));
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check.removeUserName(getContext());
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        SeekBar seekBar = binding.seekBar;
        TextView shrift = binding.editTextTextPersonName;

        TextView textView = binding.textView;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView.setText(String.valueOf(seekBar.getProgress()));
                shrift.setTextSize(seekBar.getProgress());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

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
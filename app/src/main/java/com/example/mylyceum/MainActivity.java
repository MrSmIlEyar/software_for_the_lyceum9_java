package com.example.mylyceum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button entrance;
    private TextView b;
    private EditText password;
    private ImageButton eye;
    private EditText login;
    public int flag;
    private int id;
    private int id2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrance = findViewById(R.id.button);
        password = findViewById(R.id.password);
        eye = findViewById(R.id.eye);
        login = findViewById(R.id.login);
        flag = 1;
        id = getResources().getIdentifier("@android:drawable/ic_menu_view", null, null);
        id2 = getResources().getIdentifier("@android:drawable/ic_menu_close_clear_cancel", null, null);
        password.setInputType(1);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 1) {
                    eye.setImageResource(id2);
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    flag = 0;
                } else {
                    eye.setImageResource(id);
                    password.setTransformationMethod(null);
                    flag = 1;
                }
                password.setSelection(password.length());
            }
        });


    }
}
package com.example.mylyceum;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class signup extends AppCompatActivity {
    private EditText password; //строка ввода пароля
    private ImageButton eye; //глазик на поле ввода пароля
    private EditText login; //поле ввода логина
    private Button signup; //кнопка регистрации
    private int flag; //флаг на режимы глазика
    private int id; //режим глазика, когда пароль виден
    private int id2; //режим глазика, когда пароль не виден

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.signup);
        password = findViewById(R.id.password);
        eye = findViewById(R.id.eye);
        login = findViewById(R.id.login);
        flag = 1;
        id = getResources().getIdentifier("@android:drawable/ic_hidden", null, null);
        id2 = getResources().getIdentifier("@android:drawable/ic_open", null, null);
        password.setInputType(1); //устанавливаем обычный ввод с клавиатуры
        //обрабатываем глазик для поля ввода пароля
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
        //обработка глазика для воода пароля окончена
        //обработка кнопки регистрации
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, main2.class);
                startActivity(intent);
            }
        });
        //обработка кнопки регистрации окончена
    }
}
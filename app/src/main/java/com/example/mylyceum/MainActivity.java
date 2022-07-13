package com.example.mylyceum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button entrance; //кнопка входа
    private EditText password; //строка ввода пароля
    private ImageButton eye; //глазик на поле ввода пароля
    private EditText login; //поле ввода логина
    private Button signup; //кнопка регистрации
    private int flag; //флаг на режимы глазика
    private int id; //режим глазика, когда пароль виден
    private int id2; //режим глазика, когда пароль не виден
    private DatabaseReference accounts; //инициализируем БД
    private String groupUser = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrance = findViewById(R.id.entrance);
        signup = findViewById(R.id.signup);
        password = findViewById(R.id.password);
        eye = findViewById(R.id.eye);
        login = findViewById(R.id.login);
        flag = 1;
        accounts = FirebaseDatabase.getInstance().getReference(groupUser);
        id = getResources().getIdentifier("@android:drawable/ic_hidden", null, null);
        id2 = getResources().getIdentifier("@android:drawable/ic_open", null, null);
        System.out.println(id2);
        password.setInputType(1); //устанавливаем обычный ввод с клавиатуры
        //обрабатываем глазик для поля ввода пароля
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 1) {
                    eye.setImageResource(getResources().getIdentifier("@android:drawable/ic_ihidden.xml", null, null));
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    flag = 0;
                } else {
                    eye.setImageResource(getResources().getIdentifier("@android:drawable/ic_ihidden.xml", null, null));
                    password.setTransformationMethod(null);
                    flag = 1;
                }
                password.setSelection(password.length());
            }
        });
        //обработка глазика для воода пароля окончена

        //обработка кнопки входа
        entrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, main2.class);
                startActivity(intent);
            }
        });
        //обработка кнопки входа окончена
        //обработка кнопки регистрации
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });
        //обработка кнопки регистрации окончена
    }
}
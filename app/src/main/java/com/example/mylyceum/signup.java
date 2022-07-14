package com.example.mylyceum;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    private EditText password; //строка ввода пароля
    private EditText name; //строка ввода имени
    private EditText surname; //строка ввода фамилии
    private EditText Class; //стркоа ввода класса
    private ImageButton eye; //глазик на поле ввода пароля
    private EditText login; //поле ввода логина
    private Button signup; //кнопка регистрации
    private int flag; //флаг на режимы глазика
    private int id; //режим глазика, когда пароль виден
    private int id2; //режим глазика, когда пароль не виден
    private DatabaseReference mylyceumdatabase; // инициализируем БД
    private String groupUsers = "Users"; // инициализация группы для БД

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.signup);
        password = findViewById(R.id.password);
        eye = findViewById(R.id.eye);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        Class = findViewById(R.id.class_user);
        login = findViewById(R.id.login);
        flag = 1;
        mylyceumdatabase = FirebaseDatabase.getInstance().getReference(groupUsers);
        id = getResources().getIdentifier("@android:drawable/ic_hidden", null, null);
        id2 = getResources().getIdentifier("@android:drawable/ic_open", null, null);
        password.setInputType(1); //устанавливаем обычный ввод с клавиатуры
        //обрабатываем глазик для поля ввода пароляф
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
                String idmylyceumdatabase = mylyceumdatabase.getKey();
                String username = name.getText().toString();
                String usersurname = surname.getText().toString();
                String userclass = Class.getText().toString();
                String userlogin = login.getText().toString();
                String userpassword = password.getText().toString();
                User newUser = new User(userlogin, userpassword, username, usersurname, userclass);
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(usersurname) && !TextUtils.isEmpty(userclass) &&
                        !TextUtils.isEmpty(userlogin) && !TextUtils.isEmpty(userpassword)) {
                    mylyceumdatabase.push().setValue(newUser);
                    Toast.makeText(signup.this, "Данные успешно сохранены!",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(signup.this, main2.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(signup.this, "Есть пустое поле",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        //обработка кнопки регистрации окончена
    }
}
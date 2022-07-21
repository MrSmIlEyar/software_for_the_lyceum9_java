package com.example.mylyceum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button entrance; //кнопка входа
    private EditText password; //строка ввода пароля
    private ImageButton eye; //глазик на поле ввода пароля
    private EditText login; //поле ввода логина
    private Button signup; //кнопка регистрации
    private Button vk; //кнопка перевода на ВК
    private int flag; //флаг на режимы глазика
    private int id; //режим глазика, когда пароль виден
    private int id2; //режим глазика, когда пароль не виден
    private DatabaseReference accounts; //инициализируем БД
    private String groupUser = "Users";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!(check.getUserName(MainActivity.this).length() == 0)) {
            Intent intent = new Intent(MainActivity.this, main2.class);
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User sign in

                } else {
                    // User is signed out

                }

            }
        };

        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);


        findViewById(R.id.entrance).setOnClickListener(this::onClick);
        findViewById(R.id.signup).setOnClickListener(this::onClick);
        findViewById(R.id.vk_link).setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.entrance) {
            signin(login.getText().toString(), password.getText().toString());
        } else if (view.getId() == R.id.signup) {
            registration(login.getText().toString(), password.getText().toString());
//            Intent intent = new Intent(MainActivity.this, signup.class);
//            startActivity(intent);
        } else if (view.getId() == R.id.vk_link) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/pushkinisti"));
            startActivity(intent);
        }

    }


    public void signin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            ProgressDialog pg = ProgressDialog.show(MainActivity.this, "", "Выполняется вход...", true);
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    pg.dismiss();
                    Toast.makeText(MainActivity.this, "Aвторизация успешна", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, main2.class);
                    startActivity(intent);

                }
                else if (password.length() < 6) {
                    pg.dismiss();
                    Toast.makeText(MainActivity.this, "Длина пароля меньше 6 символов", Toast.LENGTH_SHORT).show();
                }
                else {
                    pg.dismiss();
                    Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();}

            }
        });
    }

    public void registration(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    check.setUserName(MainActivity.this, login.toString());
                } else
                    Toast.makeText(MainActivity.this, "Регистрация провалена", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
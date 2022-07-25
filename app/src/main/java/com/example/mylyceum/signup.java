package com.example.mylyceum;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    private EditText password; //строка ввода пароля
    private EditText Class; //стркоа ввода класса
    private EditText login; //поле ввода логина
    private Button signup; //кнопка регистрации
    private FirebaseAuth mAuth; //токен аутенфикации пользователя
    private DatabaseReference mylyceumdatabase; // инициализируем БД

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.signup);
        password = findViewById(R.id.password_user);
        Class = findViewById(R.id.Classuser);
        login = findViewById(R.id.login_email);
        mAuth = FirebaseAuth.getInstance();
        mylyceumdatabase = FirebaseDatabase.getInstance().getReference("Users");
        password.setInputType(1);// устанавливаем обычный ввод с клавиатуры
        password.setTransformationMethod(new PasswordTransformationMethod());
        //обработка кнопки регистрации
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userclass = Class.getText().toString(); // получаем класс юзера
                String userlogin = login.getText().toString(); // получаем почту юзера
                String userpassword = password.getText().toString(); // получаем пароль юзера
                // проверяем на пустые поля
                if (!TextUtils.isEmpty(userclass) && !TextUtils.isEmpty(userlogin) && !TextUtils.isEmpty(userpassword)) {
                    registration(userlogin, userpassword); // см. метод регистрации ниже
                    Intent intent = new Intent(signup.this, main2.class); // переключение экрана
                    startActivity(intent); // переключаем экран
                }
                else {
                    Toast.makeText(signup.this, "Есть пустое поле",
                            Toast.LENGTH_SHORT).show(); // тост, если есть пустое поле
                }

            }
        });
        //обработка кнопки регистрации окончена
    }
    // метод регистрации пользователя
    public void registration(String email, String password) {
        // выполняем добавление юзера в БД
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userclass = Class.getText().toString(); // получаем класс юзера
                    User newUser = new User(userclass); // формируем юзера
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newUser); // добавляем юзера в БД под тем же ключом, под каким проходила регистрация
                    Toast.makeText(signup.this, "Регистрация успешна", Toast.LENGTH_SHORT).show(); // тост с информацией по регистрации
                    check.setUserName(signup.this, login.toString()); // добавляем юзера в базу телефона, чтобы постоянно не вводить почту и пароль
                    check.setClass(signup.this, Class.getText().toString());
                } else {
                    Toast.makeText(signup.this, "Регистрация провалена", Toast.LENGTH_SHORT).show(); // тост с информацией по регистрации
                }
            }
        });
    }
}
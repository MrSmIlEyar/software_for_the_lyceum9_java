package com.example.mylyceum;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    private EditText password; //строка ввода пароля
    private Spinner Class; //стркоа ввода класса
    private Spinner ClassBukv; //стркоа ввода класса
    String[] nachalbukv = { "А", "Б", "В", "К", "Л", "М", "Н"};
    String[] starshbukv = { "А", "Б"};
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
        Class = findViewById(R.id.class_numb);
        ClassBukv = findViewById(R.id.class_bukv);
        Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.class_number);
                String toast = choose[selectedItemPosition];
                int foo = Integer.parseInt(toast);
                if (foo < 10) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.nachal_class,
                            android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ClassBukv.setAdapter(adapter);
                }
                else{
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.high_class, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ClassBukv.setAdapter(adapter);
                }



            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        login = findViewById(R.id.login_email);
        mAuth = FirebaseAuth.getInstance();
        mylyceumdatabase = FirebaseDatabase.getInstance().getReference("Users");
        password.setInputType(1);// устанавливаем обычный ввод с клавиатуры
        password.setTransformationMethod(new PasswordTransformationMethod());
        //обработка кнопки регистрации
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userclass = Class.getSelectedItem().toString() + ClassBukv.getSelectedItem().toString();
                System.out.println(userclass);
                // Определяем разметку для использования при выборе элемента
                // Применяем адаптер к элементу spinner
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
                    String userclass = Class.getSelectedItem().toString() + ClassBukv.getSelectedItem().toString();
                    System.out.println(userclass);
                    User newUser = new User(userclass); // формируем юзера
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newUser); // добавляем юзера в БД под тем же ключом, под каким проходила регистрация
                    Toast.makeText(signup.this, "Регистрация успешна", Toast.LENGTH_SHORT).show(); // тост с информацией по регистрации
                    check.setUserName(signup.this, login.toString()); // добавляем юзера в базу телефона, чтобы постоянно не вводить почту и пароль
                } else {
                    Toast.makeText(signup.this, "Регистрация провалена", Toast.LENGTH_SHORT).show(); // тост с информацией по регистрации
                }
            }
        });
    }

}
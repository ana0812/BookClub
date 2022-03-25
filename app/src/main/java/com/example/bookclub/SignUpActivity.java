package com.example.bookclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookclub.Models.UserModel;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailInput, nameInput, passwordInput;
    private Button signUpBtn;
    private String email, name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        signUpBtn = findViewById(R.id.signUpButton);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

    }

    private void signUp(){
        email = emailInput.getText().toString();
        name = nameInput.getText().toString();
        password = passwordInput.getText().toString();

        if(email.equals("") || name.equals("") || password.equals("")){
            Toast.makeText(this, "Blank", Toast.LENGTH_LONG).show();
        }
        else{
            UserModel user = new UserModel(name, email, password);
            DBHelper dbh = new DBHelper(SignUpActivity.this);
            if(dbh.addUser(user)){
                Toast.makeText(SignUpActivity.this, "succes", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(SignUpActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
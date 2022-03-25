package com.example.bookclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookclub.Models.UserModel;

public class LogInActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button logInBtn;
    private String email, password;
    /**
     * the user that is currently logged in
     */
    static UserModel user;
    private TextView incorect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailInput = findViewById(R.id.logInEmailInput);
        passwordInput = findViewById(R.id.logInPasswordInput);
        logInBtn = findViewById(R.id.logInBtn);
        incorect = findViewById(R.id.incorectTxt);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });

    }

    private void logIn(){
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();

        if(email.equals("") || password.equals("")){
            Toast.makeText(this, "Please don't leave blank fields", Toast.LENGTH_LONG).show();
        }
        else{
            user = new UserModel(email, password);
            DBHelper dbh = new DBHelper(LogInActivity.this);

            if(dbh.checkUser(user)){
                //Toast.makeText(LogInActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, HomeActivity2.class);
                startActivity(intent);
                //TODO delete fields after logging in or add checkbox if user wants to be remembered
            }
            else{
                incorect.setVisibility(View.VISIBLE);
                incorect.setText("Email sau parola incorecte");
                Toast.makeText(LogInActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        }
        //Toast.makeText(LogInActivity.this, "succes", Toast.LENGTH_SHORT).show();
    }
}
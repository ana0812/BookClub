package com.example.bookclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bookclub.Models.BookModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button logInBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //addBooksinDB();

        logInBtn = findViewById(R.id.logInBtn);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        signUpBtn = findViewById(R.id.signUpButton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addBooksinDB(){
        List<BookModel> carti = new ArrayList<>();
        carti.add(new BookModel("1984", "George Orwell", "https://images-na.ssl-images-amazon.com/images/I/51vWjZtVpZS._SX346_BO1,204,203,200_.jpg"));
        carti.add(new BookModel("The five love languages", "Gary Chapman", "https://images-na.ssl-images-amazon.com/images/I/81oqSRw9SIL.jpg"));
        carti.add(new BookModel("The Science of getting rich", "Wallace D. Wattles", "https://www.ubuy.com/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvNjE2dDlialZUbUwuanBn.jpg"));
        carti.add(new BookModel("The seven husbands of Emily Hugo", "Taylor Jenkins Reid", "https://images-na.ssl-images-amazon.com/images/I/51hcBK1TlWL._SX329_BO1,204,203,200_.jpg"));
        carti.add(new BookModel("In another life", "Marc Levy", "https://m.media-amazon.com/images/I/41L+EBOuhqL.jpg"));
        carti.add(new BookModel("Limitless", "Jim Kwik", "https://m.media-amazon.com/images/I/41RwJnp7DaS.jpg"));
        carti.add(new BookModel("Norwegian Wood", "Haruki Murakami", "https://images-na.ssl-images-amazon.com/images/I/A1-2Tn2UGlL.jpg"));
        carti.add(new BookModel("Never split the difference", "Chris Voss", "https://images-na.ssl-images-amazon.com/images/I/91zJ3HtOJmL.jpg"));

        DBHelper dbh = new DBHelper(this);

        for(BookModel carte : carti){
            dbh.addBook(carte);
        }
    }
}
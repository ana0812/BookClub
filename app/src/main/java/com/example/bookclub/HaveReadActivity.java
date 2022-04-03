package com.example.bookclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bookclub.Adapters.BookListAdapter;
import com.example.bookclub.Models.BookModel;
import com.example.bookclub.database.DBHelper;
import com.example.bookclub.ui.start.LogInActivity;

import java.util.List;


//TODO get from intent if it is "have read" or "want to read" to know what method you call on dbh

public class HaveReadActivity extends AppCompatActivity {

    private List<BookModel> bookList;
    private DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_read);

        //get the string from other activity to know what list you are talking about
        //2 possibilities: wantToRead / haveRead
        Bundle extras = getIntent().getExtras();
        String type = extras.getString("type");

        dbh = new DBHelper(this);

        if(type.equals("haveRead")){
            bookList = dbh.getHaveRead(LogInActivity.user.getId());
        }
        else if(type.equals("wantToRead")){
            bookList = dbh.getWantToRead(LogInActivity.user.getId());
        }

        if(bookList == null){
            findViewById(R.id.relLayout).setVisibility(View.GONE);
            findViewById(R.id.faraCartiTxt).setVisibility(View.VISIBLE);
        }
        else{
            BookListAdapter adapter = new BookListAdapter(this, bookList);
            ListView listView = findViewById(R.id.haveReadListView);
            listView.setAdapter(adapter);
        }

        dbh.close();
    }

}
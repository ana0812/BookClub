package com.example.bookclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bookclub.Adapters.ListAdapter;
import com.example.bookclub.Adapters.MySummariesListAdapter;
import com.example.bookclub.Models.BookSummary;
import com.example.bookclub.database.DBHelper;
import com.example.bookclub.ui.start.LogInActivity;

import java.util.List;

//TODO adapt from ViewSummariesFragment
public class MySummariesActivity extends AppCompatActivity {

    private List<BookSummary> lista;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_summaries);

        DBHelper dbh = new DBHelper(this);
        lista = dbh.getAllSummaries(LogInActivity.user.getId());
        lv = findViewById(R.id.summariesListView);

        if(lista == null){
            lv.setVisibility(View.GONE);
            findViewById(R.id.noSummariesTxt).setVisibility(View.VISIBLE);
        }
        else{
            MySummariesListAdapter adapter = new MySummariesListAdapter(this, lista);
            lv.setAdapter(adapter);
        }

        dbh.close();
    }
}
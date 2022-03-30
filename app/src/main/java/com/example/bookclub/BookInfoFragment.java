package com.example.bookclub;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookclub.database.DBHelper;
import com.example.bookclub.ui.home.HomeFragment;
import com.example.bookclub.ui.start.LogInActivity;

public class BookInfoFragment extends Fragment {

   private FragmentBookInfoBinding binding;
   private View root;
   private Context context;
   private Button haveRead, wantToRead;


   //TODO
    //check setEnabled how works, check your written function to see if it returns what u want



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookInfoBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        context = container.getContext();

        setInfo();
        checkButtons();

        haveRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(context);

                if(dbh.addHaveRead(LogInActivity.user.getId(), HomeFragment.book.getId())){
                    Toast.makeText(context, "Added to 'Have read' list", Toast.LENGTH_LONG).show();
                    haveRead.setEnabled(false);
                }
                else{
                    Toast.makeText(context, "Error! Please try again", Toast.LENGTH_LONG).show();
                }

                dbh.close();
            }
        });

        wantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(context);

                if(dbh.addWantToRead(LogInActivity.user.getId(), HomeFragment.book.getId())){
                    Toast.makeText(context, "Added to 'Have read' list", Toast.LENGTH_LONG).show();
                    wantToRead.setEnabled(false);
                }
                else{
                    Toast.makeText(context, "Error! Please try again", Toast.LENGTH_LONG).show();
                }

                dbh.close();
            }
        });


        return root;
    }

    private void setInfo(){
        //Glide.with(root).load(book.getUrlPhoto()).into(binding.imgBook);
        Glide.with(root).load(HomeFragment.book.getUrlPhoto()).into(binding.imgBook);
        binding.bookTitle.setText(HomeFragment.book.getName());
        binding.bookAuthor.setText("by " + HomeFragment.book.getAuthor());

        haveRead = binding.haveReadBtn;
        wantToRead = binding.wantToReadBtn;
    }

    private void checkButtons(){
        DBHelper dbh = new DBHelper(context);

        if(dbh.checkWantToRead(LogInActivity.user.getId(), HomeFragment.book.getId())){
            wantToRead.setEnabled(false);
        }

        if(dbh.checkHaveRead(LogInActivity.user.getId(), HomeFragment.book.getId())){
            haveRead.setEnabled(false);
        }

        dbh.close();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
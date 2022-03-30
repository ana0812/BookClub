package com.example.bookclub.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.bookclub.Models.BookModel;
import com.example.bookclub.database.DBHelper;
import com.example.bookclub.ViewBookActivity;
import com.example.bookclub.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Context context;

    /**
     * the book that the user searched for
     */
    public static BookModel book;

    /**
     * stores the input of the user when he searches for a book
     */
    private String userBookName;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = container.getContext();

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /**
         * after user enters input setting listener for when he presses the enter button
         * if the book exists in the db, it will be displayed
         */
        binding.bookInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                    userBookName = binding.bookInput.getText().toString();
                    Log.i("Input found", userBookName);

                    DBHelper dbh = new DBHelper(context);
                    book = dbh.findBookByName(userBookName);
                    if(book!=null){
                        binding.bookName.setText(book.getName());
                        Glide.with(root).load(book.getUrlPhoto()).into(binding.imgBook);
                        binding.cardView.setVisibility(View.VISIBLE);
                    }
                    else{
                        //TODO write some message that book was not found
                    }
                    return true;
                }
                return false;
            }
        });

        /**
         * when the user presses on the photo of the book he is being sent to another page containing more info about the book
         */
        binding.imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewBookActivity.class);
                startActivity(intent);
            }
        });

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.bookclub;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.bookclub.databinding.FragmentBookInfoBinding;
import com.example.bookclub.ui.home.HomeFragment;

public class BookInfoFragment extends Fragment {

   private FragmentBookInfoBinding binding;
   private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookInfoBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        setInfo();
        return root;
    }

    private void setInfo(){
        //Glide.with(root).load(book.getUrlPhoto()).into(binding.imgBook);
        Glide.with(root).load(HomeFragment.book.getUrlPhoto()).into(binding.imgBook);
        binding.bookTitle.setText(HomeFragment.book.getName());
        binding.bookAuthor.setText("by " + HomeFragment.book.getAuthor());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
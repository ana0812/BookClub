package com.example.bookclub;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookclub.databinding.FragmentAddSummaryBinding;
import com.example.bookclub.databinding.FragmentViewSummariesBinding;

public class ViewSummariesFragment extends Fragment {

    //TODO
    //make a list view in this fragment
    //make a method in DBHelper to get all the summaries for a book
    //the method should return a list, pass that list as an argument for your list view
    //maybe add a scrollbar if the review is too large
    //maybe pass a scrollbar to your listview, if it s not made by default
    //i think that is it


    private FragmentViewSummariesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentViewSummariesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
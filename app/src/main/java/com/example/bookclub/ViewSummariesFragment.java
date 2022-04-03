package com.example.bookclub;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bookclub.Adapters.ListAdapter;
import com.example.bookclub.databinding.FragmentViewSummariesBinding;
import com.example.bookclub.database.DBHelper;
import com.example.bookclub.ui.home.HomeFragment;

import java.util.List;

public class ViewSummariesFragment extends Fragment {

    private FragmentViewSummariesBinding binding;
    private Context context;
    private ListView summariesListView;
    private List<String> summariesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentViewSummariesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        context = container.getContext();

        DBHelper dbh = new DBHelper(context);
        summariesList = dbh.getPublicSummaries(HomeFragment.book.getId());

        //daca nu exista inca rezumate scrise la aceasta carte
        if(summariesList == null){
            binding.summariesListView.setVisibility(View.GONE);
            binding.noSummariesTxt.setVisibility(View.VISIBLE);
        }
        else{
        ListAdapter adapter = new ListAdapter(context, summariesList);
        binding.summariesListView.setAdapter(adapter);
        }

        dbh.close();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
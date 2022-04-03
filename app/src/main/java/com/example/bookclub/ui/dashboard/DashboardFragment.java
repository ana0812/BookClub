package com.example.bookclub.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bookclub.HaveReadActivity;
import com.example.bookclub.MySummariesActivity;
import com.example.bookclub.databinding.FragmentDashboardBinding;
import com.example.bookclub.ui.start.LogInActivity;

public class DashboardFragment extends Fragment {

        private FragmentDashboardBinding binding;

    private TextView greetingTxt;

    private Button haveRead, wantToRead, mySummaries;

    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        DashboardViewModel dashboardViewModel =
//                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        greetingTxt = binding.greetingTxt;
        greetingTxt.setText("Welcome back, " + LogInActivity.user.getName() + "!");

        haveRead = binding.ProfileHaveReadBtn;
        wantToRead = binding.ProfileWantToreadBtn;
        mySummaries = binding.mySummariesBtn;
        context = container.getContext();

        setBtnListeners();

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void setBtnListeners(){
        haveRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HaveReadActivity.class);
                intent.putExtra("type","haveRead");
                startActivity(intent);
            }
        });

        wantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HaveReadActivity.class);
                intent.putExtra("type","wantToRead");
                startActivity(intent);
            }
        });

        mySummaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MySummariesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
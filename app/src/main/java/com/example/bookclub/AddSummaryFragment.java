package com.example.bookclub;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookclub.Models.SummaryModel;
import com.example.bookclub.database.DBHelper;
import com.example.bookclub.ui.home.HomeFragment;
import com.example.bookclub.ui.start.LogInActivity;

public class AddSummaryFragment extends Fragment {
    private FragmentAddSummaryBinding binding;
    private DBHelper dbh;
    private Context context;
    /**
     * what the user types
     */
    private String userSummary;

    /**
     * the summary that will be added in the db
     */
    private SummaryModel summaryModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddSummaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = container.getContext();

        dbh = new DBHelper(context);
        Log.i("in onCreate", "works " + String.valueOf(LogInActivity.user.getId()) + " " + String.valueOf(HomeFragment.book.getId()));

        /**
         * if the user has already written a summary to this book
         */
        if(dbh.findSummary(LogInActivity.user.getId(), HomeFragment.book.getId())){
            //Toast.makeText(context, String.valueOf(LogInActivity.user.getId()) + " " + String.valueOf(HomeFragment.book.getId()), Toast.LENGTH_LONG).show();
            binding.textNotifications.setVisibility(View.VISIBLE);
            binding.saveSummarybtn.setVisibility(View.GONE);
            binding.summaryInput.setVisibility(View.GONE);
            binding.radioGroup.setVisibility(View.GONE);
            binding.textView8.setVisibility(View.GONE);
        }
        else{
            //Toast.makeText(context, String.valueOf(LogInActivity.user.getId()) + " " + String.valueOf(HomeFragment.book.getId()), Toast.LENGTH_LONG).show();
            binding.summaryInput.setMovementMethod(new ScrollingMovementMethod());
            binding.saveSummarybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userSummary = binding.summaryInput.getText().toString();
                    if(userSummary.equals("")){
                        Toast.makeText(context, "Can't add blank text as summary", Toast.LENGTH_LONG).show();
                    }
                    else{
                        //TODO if u rlly wanna poti sa adaugi o kastchen dinaia in care sa intrebi daca e sigur si sa aiba optiunea cancel sau ok
                        summaryModel = new SummaryModel(LogInActivity.user.getId(), HomeFragment.book.getId(), userSummary);

                        //if "public" is checked, then visibility is true
                        if(binding.visibilityRadioBtn.isChecked()){
                            summaryModel.setVisible(1);
                        }
                        else
                            summaryModel.setVisible(0);

                        dbh.addSummary(summaryModel);
                        binding.saveSummarybtn.setEnabled(false);
                    }
                }
            });
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

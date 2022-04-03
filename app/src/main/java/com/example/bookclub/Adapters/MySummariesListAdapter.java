package com.example.bookclub.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookclub.Models.BookSummary;
import com.example.bookclub.R;

import java.util.List;

public class MySummariesListAdapter extends ArrayAdapter<BookSummary> {
    List<BookSummary> lista;

    public MySummariesListAdapter(Context context, List<BookSummary> lista){
        super(context, R.layout.view_user_summaries_list_item, lista);
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_user_summaries_list_item, null, false);

        TextView summaryTxt = view.findViewById(R.id.summaryTxt);
        summaryTxt.setText(lista.get(position).getSummary());

        TextView name = view.findViewById(R.id.bookNameForSummary);
        name.setVisibility(View.VISIBLE);
        name.setText(lista.get(position).getName());

        return view;
    }
}

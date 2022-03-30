package com.example.bookclub.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookclub.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<String> {

    List<String> lista;

    public ListAdapter(Context context, List<String> lista){
        super(context, R.layout.view_user_summaries_list_item, lista);
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String str = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_user_summaries_list_item, null, false);

        TextView summaryTxt = view.findViewById(R.id.summaryTxt);
        summaryTxt.setText(lista.get(position));

        return view;
    }
}

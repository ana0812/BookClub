package com.example.bookclub.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.bookclub.Models.BookModel;
import com.example.bookclub.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookListAdapter extends ArrayAdapter<BookModel> {

    List<BookModel> lista;

    public BookListAdapter(Context context, List<BookModel> lista){
        super(context, R.layout.book_list_item, lista);
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BookModel book = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.book_list_item, null, false);

        CircleImageView img = view.findViewById(R.id.profile_pic);
        Glide.with(view).load(lista.get(position).getUrlPhoto()).into(img);
        //TODO continua sa pui titlu si autorul

        TextView titlu = view.findViewById(R.id.bookNameTxt);
        titlu.setText(lista.get(position).getName());

        TextView autor = view.findViewById(R.id.authorNameTxt);
        autor.setText(lista.get(position).getAuthor());

        return view;
    }
}

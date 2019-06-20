package com.chad.bettr;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final List<Result> items;
    private final Context context;
    private RequestQueue mQueue;

    public Adapter(Context applicationContext, ArrayList<Result> items) {
        this.context = applicationContext;
        this.items = items;
//        this.pool = pool;
        mQueue = Volley.newRequestQueue(context);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Result item = items.get(i);

        viewHolder.description.setText(item.getComment());
        viewHolder.category.setText(item.getCategory());
        viewHolder.sentimental.setImageResource(R.drawable.sad);

    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView description;
        final TextView category;
        final ImageView sentimental;

        ViewHolder(final View itemView) {
            super(itemView);
            this.description = itemView.findViewById(R.id.description);
            this.category = itemView.findViewById(R.id.category);
            this.sentimental = itemView.findViewById(R.id.sentimental);
        }


    }
}
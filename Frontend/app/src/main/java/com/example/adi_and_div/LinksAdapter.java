package com.example.adi_and_div;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.ViewHolder> {

    private final Context context;
    private final List<String> links;

    public LinksAdapter(Context context, List<String> links) {
        this.context = context;
        this.links = links;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String link = links.get(position);
        holder.linkTextView.setText(link);

        // Open link on click
        holder.linkTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView linkTextView;

        ViewHolder(View itemView) {
            super(itemView);
            linkTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}

package com.loal.pillai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DrugInfoAdapter extends RecyclerView.Adapter<DrugInfoAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> titles;

    DrugInfoAdapter(Context context, List<String> titles) {
        this.layoutInflater = LayoutInflater.from(context);
        this.titles = titles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newView = layoutInflater.inflate(R.layout.drug_info_card, parent, false);
        return new ViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set title of each card
        String title = titles.get(position);
        holder.info_label.setText(title);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView info_label;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            info_label = itemView.findViewById(R.id.cardText);
        }
    }
}

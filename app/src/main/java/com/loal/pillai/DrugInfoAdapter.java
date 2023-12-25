package com.loal.pillai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DrugInfoAdapter extends RecyclerView.Adapter<DrugInfoAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> titles;
    private List<Boolean> selectedOptions;

    public DrugInfoAdapter(Context context, List<String> titles, List<Boolean> selectedOptions) {
        this.layoutInflater = LayoutInflater.from(context);
        this.titles = titles;
        this.selectedOptions = selectedOptions;
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

        // Set on click action
        holder.card_background.setOnClickListener(view -> {
            // Toggle selected state
            selectedOptions.set(position, !selectedOptions.get(position));

            // Set correct color
            int color;
            if(selectedOptions.get(position)) {
                color = ContextCompat.getColor(holder.itemView.getContext(), R.color.selected);
            } else {
                color = ContextCompat.getColor(holder.itemView.getContext(), R.color.deselected);
            }

            holder.card_background.setCardBackgroundColor(color);
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView info_label;
        CardView card_background;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find elements
            info_label = itemView.findViewById(R.id.cardText);
            card_background = itemView.findViewById(R.id.cardBackground);
        }
    }
}

package com.loal.pillai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class DrugInfoActivity extends AppCompatActivity {

    String drugEANCode;
    String CODE_ALVEDON = "7046260070127";

    LinearLayout backBtn;

    TextView drugNameText;

    RecyclerView recyclerView;

    ArrayList<String> options;
    HashMap<String,Boolean> selectedOptions;

    DrugInfoAdapter adapter;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);

        // Get additional info
        Intent intent = getIntent();
        drugEANCode = intent.getStringExtra("CODE");

        // Get all options
        options = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.drug_info_options)));

        // All deselected
        selectedOptions = new HashMap<String,Boolean>();
        for (String title : options) {selectedOptions.put(title ,false);}


        // Find views
        drugNameText = findViewById(R.id.drugNameText);
        recyclerView = findViewById(R.id.drugInfoRecycler);
        backBtn = findViewById(R.id.backBtn);
        searchView = findViewById(R.id.searchView);

        // Back button
        backBtn.setOnClickListener(view -> this.finish());

        // Code result
        if(drugEANCode.equals(CODE_ALVEDON)) {
            drugNameText.setText("Alvedon");
        } else {
            drugNameText.setText("Not recognized");
        }

        // Recycler view with options
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new DrugInfoAdapter(this, options, selectedOptions);
        recyclerView.setAdapter(adapter);

        // Search options
        searchView.clearFocus();
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterOptions(s);
                return false;
            }
        });

    }

    private void filterOptions(String text) {
        //TODO: We need to save old selections and new ones when user does a search...
        ArrayList<String> filteredTitles = new ArrayList<>();
        HashMap<String,Boolean> filteredSelection = new HashMap<String,Boolean>();

        adapter.setFilteredList(options, selectedOptions);

        // Add all titles that contain the searched string
        for (String title : options) {
            if (title.toLowerCase().contains(text.toLowerCase())) {
                filteredTitles.add(title);
                filteredSelection.put(title, selectedOptions.get(title));
            }
        }

        // Output
        if(filteredTitles.isEmpty()) {
            Toast.makeText(this, "No options available", Toast.LENGTH_SHORT).show();
        }
        adapter.setFilteredList(filteredTitles, filteredSelection);
    }
}
package com.loal.pillai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class DrugInfoActivity extends AppCompatActivity {

    String drugEANCode;
    String CODE_ALVEDON = "7046260070127";

    LinearLayout backBtn;

    TextView drugNameText;

    RecyclerView recyclerView;

    ArrayList<String> options;

    DrugInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);

        // Get additional info
        Intent intent = getIntent();
        drugEANCode = intent.getStringExtra("CODE");

        // Create all options
        options = new ArrayList<>(Arrays.asList(
                "Side effects",
                "Contraindications",
                "How to take it",
                "Pregnancy",
                "Interactions",
                "Composition",
                "Purpose of use",
                "Warnings",
                "Additional info"
        ));

        // Find views
        drugNameText = findViewById(R.id.drugNameText);
        recyclerView = findViewById(R.id.drugInfoRecycler);
        backBtn = findViewById(R.id.backBtn);

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
        adapter = new DrugInfoAdapter(this, options);
        recyclerView.setAdapter(adapter);
    }
}
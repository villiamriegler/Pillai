package com.loal.pillai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DrugInfoActivity extends AppCompatActivity {

    String drugEANCode;
    String CODE_ALVEDON = "7046260070127";

    TextView drugNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);

        // Get additional info
        Intent intent = getIntent();
        drugEANCode = intent.getStringExtra("CODE");

        drugNameText = findViewById(R.id.drugNameText);

        if(drugEANCode.equals(CODE_ALVEDON)) {
            drugNameText.setText("Alvedon");
        } else {
            drugNameText.setText("Not recognized");
        }
    }
}
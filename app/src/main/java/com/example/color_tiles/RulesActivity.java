package com.example.color_tiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.color_tiles.databinding.ActivityRulesBinding;

public class RulesActivity extends AppCompatActivity {

    private ActivityRulesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRulesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.buttonRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RulesActivity.this,Selector_page.class);
                startActivity(intent);
            }
        });

    }
}
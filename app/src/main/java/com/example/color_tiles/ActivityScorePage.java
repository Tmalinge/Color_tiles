package com.example.color_tiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.color_tiles.databinding.ActivityGameBinding;
import com.example.color_tiles.databinding.ActivityScorePageBinding;

public class ActivityScorePage extends AppCompatActivity {

    private ActivityScorePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScorePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.textView6.setText("Hello");
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityScorePage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

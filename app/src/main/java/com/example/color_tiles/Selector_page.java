package com.example.color_tiles;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.color_tiles.databinding.ActivitySelectorPageBinding;

public class Selector_page extends AppCompatActivity {

    private ActivitySelectorPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectorPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
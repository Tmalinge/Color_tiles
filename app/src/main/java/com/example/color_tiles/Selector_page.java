package com.example.color_tiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    @Override
    protected void onResume() {
        super.onResume();
        binding.imageButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Selector_page.this, ActivityGame.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", 4);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.imageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Selector_page.this, ActivityGame.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", 6);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.imageButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Selector_page.this, ActivityGame.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", 8);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        binding.imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selector_page.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
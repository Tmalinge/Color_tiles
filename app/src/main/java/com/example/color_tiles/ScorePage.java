package com.example.color_tiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.example.color_tiles.databinding.ActivityScorePageBinding;
import java.util.ArrayList;
import java.util.List;


public class ScorePage extends AppCompatActivity {
    private ActivityScorePageBinding binding;
    private List<Fragment_Nom_Score> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);
        fragments = new ArrayList<>();
        fragments.add(Fragment_Nom_Score.newInstance(1,"gouverneur",30));
        fragments.add(Fragment_Nom_Score.newInstance(2,"president",12));
        fragments.add(Fragment_Nom_Score.newInstance(3,"ministre",10));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for(Fragment_Nom_Score frag : fragments) {
            ft.add(R.id.FragLayout,frag);
        }
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {

            binding.button.setOnClickListener(v -> {
                Intent intent = new Intent(ScorePage.this, MainActivity.class);
                startActivity(intent);
            });}
        catch(Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
    }
}
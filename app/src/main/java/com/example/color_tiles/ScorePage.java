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
    private static int score1=0;
    private static int score2=0;
    private static int score3=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScorePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //get score
        Intent intent = getIntent();
        int scorePlayed= intent.getIntExtra("score",0);
        if(scorePlayed==0){
            binding.votreNomTextView.setText("");
        }
        else{
            binding.votreNomTextView.setText(String.valueOf(scorePlayed));
        }

        fragments = new ArrayList<>();

        // Ranking
        if(scorePlayed>score3){
            score3 = scorePlayed;
        }
        if(score3>=score2){
            int save=score2;
            score2=score3;
            score3=save;
        }
        if(score2>=score1){
            int save=score1;
            score1=score2;
            score2=save;
        }
        if(score1!=0) fragments.add(Fragment_Nom_Score.newInstance(1,score1));
        if(score2!=0) fragments.add(Fragment_Nom_Score.newInstance(2,score2));
        if(score3!=0) fragments.add(Fragment_Nom_Score.newInstance(3,score3));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for(Fragment_Nom_Score frag : fragments) {
            ft.add(R.id.FragLayout,frag);
        }
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScorePage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
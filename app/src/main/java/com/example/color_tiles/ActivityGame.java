package com.example.color_tiles;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.ArrayList;

import com.example.color_tiles.databinding.ActivityGameBinding;

public class ActivityGame extends AppCompatActivity {

    private ActivityGameBinding binding;
    private GridView gridView;
    ArrayList<Integer> integerRes = new ArrayList<>();

    int data = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gridView = (GridView) findViewById(R.id.GridView);
        gridView.setAdapter(new GridAdapter());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        data = (int) bundle.getSerializable("data");

        for(int i=0; i<data; i++){
            for(int j=0; j<data; j++){
                integerRes.add(R.drawable.black);
            }
        }
    }
    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return integerRes.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageButton imageButton=new ImageButton(getApplicationContext());

            binding.GridView.setNumColumns(data);

            imageButton.setLayoutParams(new LinearLayout.LayoutParams(binding.GridView.getWidth()/(data +1), binding.GridView.getHeight()/(data +1)));
            // for better result in various devices calculate width at runtime and measure height accordingly

            imageButton.setBackgroundColor(Color.BLACK);

            imageButton.setImageResource(integerRes.get(i));
            imageButton.setTag(R.drawable.black);

            //imageButton.setTag();

            imageButton.setScaleType(ImageButton.ScaleType.CENTER_CROP);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.textView.setText("Tag : "+ imageButton.getTag().toString());
                    /*if(imageButton.getTag() == 1){

                    }*/
                }
            });

            return imageButton;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        binding.textView.setText("data : " + data);

    }


}
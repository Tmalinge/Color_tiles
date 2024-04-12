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
import java.util.Arrays;

import com.example.color_tiles.databinding.ActivityGameBinding;
import com.google.android.material.tabs.TabLayout;

public class ActivityGame extends AppCompatActivity {

    private ActivityGameBinding binding;
    private GridView gridView;
    ArrayList<Integer> integerRes = new ArrayList<>();
    ArrayList<Tiles> arrayTiles = new ArrayList<>();
    int idGenerator;
    int data = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gridView = (GridView) findViewById(R.id.GridView);
        gridView.setAdapter(new GridAdapter());
        idGenerator = 0;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        data = (int) bundle.getSerializable("data");

        for(int i=0; i<(data); i++){
            for(int j=0; j<(data); j++){
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

            imageButton.setLayoutParams(new LinearLayout.LayoutParams(binding.GridView.getWidth()/(data+1), binding.GridView.getHeight()/(data+1)));
            // for better result in various devices calculate width at runtime and measure height accordingly

            imageButton.setBackgroundColor(Color.BLACK);

            imageButton.setImageResource(integerRes.get(i));
            imageButton.setId(idGenerator);
            arrayTiles.add(new Tiles(imageButton.getId(), State.BLACK, imageButton));

            incrementIdGenerator();

            imageButton.setScaleType(ImageButton.ScaleType.CENTER_CROP);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.textView.setText("ImgBtn Id : "+ imageButton.getId());
                    binding.textView5.setText(".");
                    for(Tiles tiles : arrayTiles){
                        changeBGColorNoError(tiles.getId());
                    }
                    for(Tiles tiles : arrayTiles) {
                        if (tiles.getId() == imageButton.getId()) {
                            switch (tiles.getState()) {
                                case BLACK:
                                    imageButton.setImageResource(R.drawable.red);
                                    arrayTiles.get(tiles.getId()).setState(State.RED);
                                    break;
                                case RED:
                                    imageButton.setImageResource(R.drawable.blue);
                                    arrayTiles.get(tiles.getId()).setState(State.BLUE);
                                    break;
                                case BLUE:
                                    imageButton.setImageResource(R.drawable.black);
                                    arrayTiles.get(tiles.getId()).setState(State.BLACK);
                                    break;
                            }
                        }
                    }
                    //binding.textView.setText("Check End Of Game : "+ checkEndOfGame());
                    if (checkEndOfGame() == 1){
                        binding.textView.setText("End Of Game");
                        if(!checkRegle()){
                            binding.textView5.setText("No Error");
                            System.out.println("END OF THE GAME, NO MORE ERROR");
                        }
                    }


                }

            });

            return imageButton;
        }
    }

    private void incrementIdGenerator(){
        this.idGenerator++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.textView.setText("data : " + data);

    }

    public int checkEndOfGame (){
        int blackTiles = 0;
        int noBlackTiles = 0;
        for (int index=4; index< arrayTiles.size();index++){
            if(arrayTiles.get(index).getState().equals(State.BLACK)){
                blackTiles++;
            }
            else {
                noBlackTiles++;
            }
        }
        return (noBlackTiles/(data*data));
    }

    private boolean checkRegle(){
        int index = 4;
        int nbRed=0;
        int nbBlue=0;
        ArrayList<Integer> listSameColor = new ArrayList<>();
        for(int i=0; i<data; i++){
            for(int j=0; j<data; j++){
                if(((index-4)%data)<(data-2)){
                    // Check Horisontal (3 tiles same color)
                    if((arrayTiles.get(index).getState() == State.RED && arrayTiles.get(index+1).getState() == State.RED && arrayTiles.get(index+2).getState() == State.RED) || (arrayTiles.get(index).getState() == State.BLUE && arrayTiles.get(index+1).getState() == State.BLUE && arrayTiles.get(index+2).getState() == State.BLUE) ){
                        binding.textView5.setText("Error 3 Tiles Folow "+ index + " | "+ (index+1) + " | "+ (index+2));
                        changeBGColorError(index);
                        changeBGColorError(index+1);
                        changeBGColorError(index+2);
                        return true;

                    }
                }
                // Check Vertical (3 tiles Same color)
                if((index-4) < data*(data-2)){
                    if((arrayTiles.get(index).getState() == State.RED && arrayTiles.get(index+data).getState() == State.RED && arrayTiles.get(index+data*2).getState() == State.RED) || (arrayTiles.get(index).getState() == State.BLUE && arrayTiles.get(index+data).getState() == State.BLUE && arrayTiles.get(index+data*2).getState() == State.BLUE) ){
                        binding.textView5.setText("Error 3 Tiles Folow "+ index + " | "+ (index+data) + " | "+ (index+data*2));
                        changeBGColorError(index);
                        changeBGColorError(index+data);
                        changeBGColorError(index+data*2);
                        return true;

                    }
                }


                index++;



            }

        }
        // Check Coloun & Line

        nbRed=0;
        nbBlue=0;
        listSameColor.clear();
        index = 4;
        for(int y=0;y<data;y++) {

            if(arrayTiles.get(index+4).getState() == State.RED){
                nbRed++;
                listSameColor.add(index+4);
            }
            else if(arrayTiles.get(index+4).getState() == State.BLUE){
                nbBlue++;
                listSameColor.add(index+4);
            }
            index=+4;
        }
        if(nbBlue!=nbRed && nbBlue+nbRed==data){
            for(int id : listSameColor){
                changeBGColorError(id);
            }
            return true;
        }




        return false;
    }

    private void changeBGColorError(int index){
        arrayTiles.get(index).getImageButton().setBackgroundColor(Color.YELLOW);
    }
    private void changeBGColorNoError(int index){
        arrayTiles.get(index).getImageButton().setBackgroundColor(Color.BLACK);
    }


}
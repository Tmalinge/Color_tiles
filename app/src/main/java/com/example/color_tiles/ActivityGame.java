package com.example.color_tiles;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Random;

import com.example.color_tiles.databinding.ActivityGameBinding;

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

            // Set Color

            imageButton.setBackgroundColor(Color.BLACK);
            imageButton.setImageResource(integerRes.get(i));
            imageButton.setId(idGenerator);
            arrayTiles.add(new Tiles(imageButton.getId(), State.BLACK, imageButton));

            if(idGenerator==(data*data+3)){
                initGame();
            }

            incrementIdGenerator();

            imageButton.setScaleType(ImageButton.ScaleType.CENTER_CROP);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(Tiles tiles : arrayTiles) {
                        if (tiles.getId() == imageButton.getId()) {
                            if(tiles.isLocked()){
                                return;
                            }
                        }
                    }

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
                    if (percentEndOfGame() == 1){
                        binding.textView.setText("End Of Game ?");
                        if(!checkRegle()){
                            binding.textView5.setText("END OF THE GAME !");
                            System.out.println("END OF THE GAME !");
                            Intent intent = new Intent(ActivityGame.this, ScorePage.class);
                            startActivity(intent);
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
        // Init Gmae

    }

    public int percentEndOfGame(){
        int noBlackTiles = 0;
        for (int index=4; index< arrayTiles.size();index++){
            if(!arrayTiles.get(index).getState().equals(State.BLACK)){
                noBlackTiles++;
            }
        }
        return (noBlackTiles/(data*data));
    }

    private boolean checkRegle(){
        int index = 4;
        int nbRed=0;
        int nbBlue=0;
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
        // Get Line  Column
        index = 4;
        ArrayList<ArrayList<TilesCheck>> listColumn = new ArrayList<>();
        ArrayList<ArrayList<TilesCheck>> listLine = new ArrayList<>();
        for(int i=0; i<data; i++){
            ArrayList<TilesCheck> column = new ArrayList<>();
            for(int y=0; y<data; y++) {
                column.add(new TilesCheck(index+data*y, arrayTiles.get(index+data*y).getState()));
            }
            listColumn.add(column);
            index++;
        }
        index = 4;
        for(int i=0; i<data; i++){
            ArrayList<TilesCheck> line = new ArrayList<>();
            for(int y=0; y<data; y++) {
                line.add(new TilesCheck(index+y, arrayTiles.get(index + y).getState()));
            }
            listLine.add(line);
            index=index+data;
        }

        // Check Equal color in same Line/column

        if(checkEqualColor(listColumn)) return true;
        if(checkEqualColor(listLine)) return true;

        if(checkTowLineColumn(listColumn)) return true;
        if(checkTowLineColumn(listLine)) return true;


        return false;
    }

    private void changeBGColorError(int index){
        arrayTiles.get(index).getImageButton().setBackgroundColor(Color.YELLOW);
    }
    private void changeBGColorNoError(int index){
        arrayTiles.get(index).getImageButton().setBackgroundColor(Color.BLACK);
    }
    private boolean checkEqualColor( ArrayList<ArrayList<TilesCheck>> list){
        for(int i=0; i<data; i++){
            int nbRed=0;
            int nbBlue=0;
            ArrayList<Integer> listId = new ArrayList<>();
            for(int j=0; j<data; j++){
                if(list.get(i).get(j).getState().equals(State.RED)) nbRed++;
                else if(list.get(i).get(j).getState().equals(State.BLUE)) nbBlue++;
                listId.add(list.get(i).get(j).getId());
            }
            if(nbRed != nbBlue && (nbBlue+nbRed == data)){
                for (Integer id : listId){
                    changeBGColorError(id);
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkTowEqualList(@NonNull ArrayList<TilesCheck> list1, ArrayList<TilesCheck> list2){
        ArrayList<Integer> listId = new ArrayList<>();
        Boolean equalList = true;
        for(int i=0; i<list1.size(); i++){
            if(!list1.get(i).getState().equals(list2.get(i).getState())){
                equalList=false;
            }
        }

        if(equalList) {
            for(TilesCheck tilesId : list1){
                listId.add(tilesId.getId());
            }
            for(TilesCheck tilesId : list2){
                listId.add(tilesId.getId());
            }
            for(Integer id : listId) changeBGColorError(id);
            binding.textView5.setText("2 Equal list : "+ listId);
            return true;
        }
        return false;
    }
    private boolean checkTowLineColumn(ArrayList<ArrayList<TilesCheck>> list){
        for(ArrayList<TilesCheck> list1 : list){
            for(ArrayList<TilesCheck> list2 : list){
                if(!(list1.get(0).getId()==list2.get(0).getId())){
                    return checkTowEqualList(list1, list2);
                }
            }
        }
        return false;
    }

    private void initGame(){
        int nbTiles = (data*data)/3; // Fill 1/3 of the grid
        Random rand = new Random();
        for(int i=0; i<nbTiles;i++){
            ArrayList<Integer> listOfFreeId = new ArrayList<>();
            for(Tiles tiles : arrayTiles){
                if(tiles.getState().equals(State.BLACK) && tiles.getId() >=4){
                    listOfFreeId.add(tiles.getId());
                }
            }
            int randId = listOfFreeId.get(rand.nextInt(listOfFreeId.size())); // Random number
            int randColor = rand.nextInt(2); // 0 Or 1
            if(randColor==1) {
                arrayTiles.get(randId).getImageButton().setImageResource(R.drawable.red_lock);
                arrayTiles.get(randId).setState(State.RED);
                arrayTiles.get(randId).setLocked(true);
            }
            else {
                arrayTiles.get(randId).getImageButton().setImageResource(R.drawable.blue_lock);
                arrayTiles.get(randId).setState(State.BLUE);
                arrayTiles.get(randId).setLocked(true);
            }

            if(checkRegle()){
                i--;
                arrayTiles.get(randId).getImageButton().setImageResource(R.drawable.black);
                arrayTiles.get(randId).setState(State.BLACK);
                arrayTiles.get(randId).setLocked(false);
            }
        }



    }

}
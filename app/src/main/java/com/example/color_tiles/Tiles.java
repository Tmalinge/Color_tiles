package com.example.color_tiles;

import android.widget.ImageButton;

public class Tiles {

    private int id;
    private State state;
    private boolean locked;
    private ImageButton imageButton;

    Tiles(int id, State state, ImageButton imageButton){
        this.id = id;
        this.state = state;
        this.locked = false;
        this.imageButton = imageButton;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }
}

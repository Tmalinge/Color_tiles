package com.example.color_tiles;

import android.widget.ImageButton;

public class TilesCheck {
    private int id;
    private State state;
    TilesCheck(int id, State state){
        this.id = id;
        this.state = state;
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
}

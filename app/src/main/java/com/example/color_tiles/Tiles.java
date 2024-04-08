package com.example.color_tiles;

public class Tiles {

    private int id;
    private State state;
    private boolean locked;

    Tiles(int id, State state){
        this.id = id;
        this.state = state;
        this.locked = false;
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
}

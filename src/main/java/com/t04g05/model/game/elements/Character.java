package com.t04g05.model.game.elements;

import com.t04g05.model.Position;

public class Character extends Element {
    private int lives;

    public Character(int x, int y) {
        super(x, y);
        this.lives = 3;
    }

    public void decreaseLives(){
        this.lives--;
    }

    public int getLives(){
        return lives;
    }
}

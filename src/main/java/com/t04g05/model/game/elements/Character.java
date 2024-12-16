package com.t04g05.model.game.elements;

import com.t04g05.model.Position;

public class Character extends Element {
    private int lives;

    public Character(Position position) {
        super(position.getX(), position.getY());
        this.lives = 3;
    }

    public void decreaseLives(){
        this.lives--;
    }

    public int getLives(){
        return lives;
    }
}

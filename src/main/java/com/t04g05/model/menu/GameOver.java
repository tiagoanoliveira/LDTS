package com.t04g05.model.menu;

import com.t04g05.model.game.elements.Character;

public class GameOver {
    private Character character;
    private int score;
    public GameOver(Character character, int score){
        this.character = character;
        this.score = character.getScore();
    }
}

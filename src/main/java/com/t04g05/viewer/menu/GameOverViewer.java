package com.t04g05.viewer.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.GameOver;
import com.t04g05.model.game.elements.Character;


public class GameOverViewer {
    private final GameOver gameover;
    private final Character character;

    public GameOverViewer(GameOver gameover, Character character){
        this.gameover = gameover;
        this.character = character;
    }

    public void draw(GUI gui){
        gui.drawText(40, 20, "GAME OVER", "#FFFFFF");
        gui.drawText(37, 21, "Total Score: " + character.getScore(), "#FFFFFF");
    }
}

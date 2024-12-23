package com.t04g05.viewer.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.Character;


public class GameOverViewer {
    private final Character character;

    public GameOverViewer(Character character){
        this.character = character;
    }

    public void draw(GUI gui){
        gui.drawText(30, 19, "                                      ", "#000", "#EEAD2D");
        gui.drawText(30, 20, " ==================================== ", "#000", "#EEAD2D");
        gui.drawText(30, 21, "                                      ", "#000", "#EEAD2D");
        gui.drawText(30, 22, "              GAME OVER!              ", "#000", "#EEAD2D");
        gui.drawText(30, 23, "                                      ", "#000", "#EEAD2D");
        gui.drawText(30, 24, " ==================================== ", "#000", "#EEAD2D");
        gui.drawText(30, 25, "                                      ", "#000", "#EEAD2D");
        gui.drawText(30, 26, "          Ficaste sem vidas!          ", "#000", "#EEAD2D");
        gui.drawText(30, 27, "                                      ", "#000", "#EEAD2D");
        gui.drawText(30, 28, " ==================================== ", "#000", "#EEAD2D");
        gui.drawText(30, 29, "                                      ", "#000", "#EEAD2D");
        gui.drawText(30, 30, "            Total Score: " + character.getScore() + "            ", "#000", "#EEAD2D");
        gui.drawText(30, 31, "                                      ", "#000", "#EEAD2D");
    }
}

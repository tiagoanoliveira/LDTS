package com.t04g05.viewer.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.Character;


public class GameOverViewer {
    private final Character character;

    public GameOverViewer(Character character){
        this.character = character;
    }

    public void draw(GUI gui){
        gui.drawText(25, 15, "                                      ", "#000", "#EEAD2D");
        gui.drawText(25, 16, " ==================================== ", "#000", "#EEAD2D");
        gui.drawText(25, 17, "                                      ", "#000", "#EEAD2D");
        gui.drawText(25, 18, "              GAME OVER!              ", "#000", "#EEAD2D");
        gui.drawText(25, 19, "                                      ", "#000", "#EEAD2D");
        gui.drawText(25, 20, " ==================================== ", "#000", "#EEAD2D");
        gui.drawText(25, 21, "                                      ", "#000", "#EEAD2D");
        gui.drawText(25, 22, "          Ficaste sem vidas!          ", "#000", "#EEAD2D");
        gui.drawText(25, 23, "                                      ", "#000", "#EEAD2D");
        gui.drawText(25, 24, " ==================================== ", "#000", "#EEAD2D");
        gui.drawText(25, 25, "                                      ", "#000", "#EEAD2D");
        gui.drawText(25, 26, "            Total Score: " + character.getScore() + "            ", "#000", "#EEAD2D");
        gui.drawText(25, 27, "                                      ", "#000", "#EEAD2D");
        gui.drawText(25, 28, "                                      ", "#000", "#00FF00");
        gui.drawText(25, 29, "  Pressione qualquer tecla para sair  ", "#000", "#00FF00");
        gui.drawText(25, 30, "                                      ", "#000", "#00FF00");
    }
}

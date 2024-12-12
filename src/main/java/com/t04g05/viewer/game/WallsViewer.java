package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.GameElement;
import com.t04g05.model.game.elements.Walls;

public class WallsViewer {
    public void draw(GUI gui, GameElement element) {
        if (element instanceof Walls) {
            Walls wall = (Walls) element;
            gui.drawElement(wall.getPosition(), '#', "#808080", "#808080"); // Branco no cinzento
        }
    }
}

package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.Walls;

public class WallsViewer implements ElementViewer<Walls>{
    public void draw(GUI gui, Walls wall) {
        gui.drawWall(wall.getPosition());
    }
}

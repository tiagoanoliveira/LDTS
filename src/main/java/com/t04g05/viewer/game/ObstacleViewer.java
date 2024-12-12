package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.GameElement;
import com.t04g05.model.game.elements.Obstacle;

public class ObstacleViewer {
    public void draw(GUI gui, GameElement element) {
        if (element instanceof Obstacle) {
            Obstacle obstacle = (Obstacle) element;
            gui.drawElement(obstacle.getPosition(), '#', "#FF6347", "#FF6347");  // Cinzento
        }
    }
}

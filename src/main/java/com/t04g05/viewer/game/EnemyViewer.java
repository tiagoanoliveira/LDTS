package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.GameElement;
import com.t04g05.model.game.elements.Enemy;

public class EnemyViewer {
    public void draw(GUI gui, GameElement element) {
        if (element instanceof Enemy) {
            Enemy enemy = (Enemy) element;
            gui.drawElement(enemy.getPosition(), 'E', "#FF0000","#FF0000");  // Vermelho
        }
    }
}

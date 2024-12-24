package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.Enemy;

public class EnemyViewer implements ElementViewer<Enemy>{
    @Override
    public void draw(GUI gui, Enemy enemy) {
        gui.drawEnemy(enemy.getPosition());
    }
}

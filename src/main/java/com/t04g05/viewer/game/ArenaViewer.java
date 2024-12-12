package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.GameElement;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Enemy;
import com.t04g05.model.game.elements.Obstacle;
import com.t04g05.model.game.elements.Walls;

public class ArenaViewer {
    private final Arena arena;

    public ArenaViewer(Arena arena) {
        this.arena = arena;
    }

    public void draw(GUI gui) {
        for (GameElement element : arena.getElements()) {
            if (element instanceof Character) {
                new CharacterViewer().draw(gui, element);
            } else if (element instanceof Enemy) {
                new EnemyViewer().draw(gui, element);
            } else if (element instanceof Obstacle) {
                new ObstacleViewer().draw(gui, element);
            } else if (element instanceof Walls) {
                new WallsViewer().draw(gui, element);
            }
        }
        gui.drawElement(arena.getGoalPosition(), 'O', "#FFFF33", "#000000");
    }
}


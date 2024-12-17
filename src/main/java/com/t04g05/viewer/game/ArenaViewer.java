package com.t04g05.viewer.game;

import  com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.elements.*;
import com.t04g05.model.game.elements.Character;

public class ArenaViewer {
    private final Arena arena;

    public ArenaViewer(Arena arena) {
        this.arena = arena;
    }

    public void draw(GUI gui) {
        for (Element element : arena.getElements()) {
            if (element instanceof Character) {
                new CharacterViewer().draw(gui, element);
            } else if (element instanceof Enemy) {
                new EnemyViewer().draw(gui, element);
            } else if (element instanceof Coin) {
                new CoinViewer().draw(gui, element);
            } else if (element instanceof Walls) {
                new WallsViewer().draw(gui, element);
            }
        }
        gui.drawElement(arena.getGoalPosition(), 'O', "#FFFF33", "#000000");
    }
}
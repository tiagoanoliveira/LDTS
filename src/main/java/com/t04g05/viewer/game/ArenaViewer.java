package com.t04g05.viewer.game;

import  com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.arena.Arena4;
import com.t04g05.model.game.elements.*;
import com.t04g05.viewer.Viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaViewer extends Viewer<Arena> {
    private final Arena arena;
    public ArenaViewer(Arena arena) {
        super(arena);
        this.arena = arena;
    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        gui.setBackgroundColor("#9f958d"); // Define a cor do fundo
        drawElements(gui, new ArrayList<>(getModel().getWalls()), new WallsViewer());
        drawElements(gui, new ArrayList<>(getModel().getEnemies()), new EnemyViewer());
        drawElement(gui, getModel().getCharacter(), new CharacterViewer());
        for (Coin coin : getModel().getCoins()) {
            gui.drawCoin(coin.getPosition());
        }
        gui.drawScoreLives(arena.getCharacter().getScore(), arena.getCharacter().getLives());
        if (arena instanceof Arena4) {
            gui.drawGoldenDoor(arena.getDoorPosition()); // Porta dourada
        } else {
            gui.drawDoor(arena.getDoorPosition()); // Porta normal
        }
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) throws IOException {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) throws IOException {
        viewer.draw(gui, element);
    }
}
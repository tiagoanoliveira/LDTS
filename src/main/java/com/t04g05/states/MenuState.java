package com.t04g05.states;

import com.t04g05.controller.menu.MenuController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.viewer.menu.MenuViewer;

public class MenuState extends GameState {
    private final MenuController controller;
    private final MenuViewer viewer;

    public MenuState(MenuController controller) {
        this.controller = controller;
        this.viewer = new MenuViewer(controller.getMenu());
    }

    @Override
    public void step(GUI gui) {
        try {
            gui.clear();
            viewer.draw(gui);
            gui.refresh();

            GUI.ACTION action = gui.getNextAction();
            GameState nextState = controller.processInput(action);
            if (nextState != null) {
                setNextState(nextState);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Arena getArena() {
        return null;
    }
}

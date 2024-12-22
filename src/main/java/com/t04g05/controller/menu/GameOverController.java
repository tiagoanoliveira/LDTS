package com.t04g05.controller.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.GameOver;
import com.t04g05.states.GameState;
import com.t04g05.states.MenuState;

public class GameOverController {
    private final GameOver gameover;
    private final MenuController menuController;
    private GUI gui;

    public GameOverController(MenuController menuController, GUI gui){
        this.gameover = null;
        this.menuController = menuController;
        this.gui = gui;
    }

    public GameState processInput(GUI.ACTION action){
        switch (action){
            case ENTER:
                return new MenuState(menuController, gui);
            default:
                break;
        }
        return null;
    }
}

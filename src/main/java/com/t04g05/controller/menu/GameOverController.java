package com.t04g05.controller.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.GameOver;
import com.t04g05.model.menu.Menu;
import com.t04g05.states.GameState;
import com.t04g05.states.MenuState;

public class GameOverController {
    private final GameOver gameover;

    public GameOverController(GameOver gameover){
        this.gameover = null;
    }

    public GameState processInput(GUI.ACTION action){
        switch (action){
            default:
                break;
        }
        return null;
    }
}

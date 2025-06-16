package com.t04g05.controller.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Win;
import com.t04g05.states.GameState;
import com.t04g05.states.MenuState;

public class WinController {
    private final Win win;

    public WinController(Win win) {
        this.win = win;
    }

    public GameState processInput(GUI.ACTION action) {
        if (action == GUI.ACTION.QUIT) {
            return null;  // Fecha o jogo
        } else if (action == GUI.ACTION.ENTER) {
            return new MenuState(new MenuController(win.getMenu()));  // Volta ao menu principal
        }
        return null;  // Mantém o estado atual
    }
}

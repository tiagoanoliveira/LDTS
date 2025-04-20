package com.t04g05.controller.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Menu;
import com.t04g05.states.GameState;
import com.t04g05.states.Level1State;
import com.t04g05.states.MenuState;

public class MenuController {
    private final Menu menu;

    public MenuController(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public GameState processInput(GUI.ACTION action) {
        switch (action) {
            case UP:
                menu.previousOption();
                break;
            case DOWN:
                menu.nextOption();
                break;
            case ENTER:
                if (menu.getMode() == Menu.Mode.MAIN_MENU) {
                    if (menu.isStartGameSelected()) {
                        System.out.println("Boa sorte. Tens que chegar à Porta de Ouro sem ser morto pelos inimigos!");
                        System.out.println("Tens 3 vidas. Podes controlar as vidas restantes no topo do jogo.");
                        return new Level1State(); // Transição para o nível 1
                    } else if (menu.isInstructionsSelected()) {
                        menu.setMode(Menu.Mode.INSTRUCTIONS);
                    } else if (menu.isExitSelected()) {
                        return null; // Sair do jogo
                    }
                } else if (menu.getMode() == Menu.Mode.INSTRUCTIONS) {
                    if (menu.isBackSelected()) {
                        menu.setMode(Menu.Mode.MAIN_MENU);
                    }
                }
                break;
            case ESC, QUIT:
                return null; // Sair do jogo
            default:
                break;
        }
        return new MenuState(this);    }
}


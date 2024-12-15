package com.t04g05;

import com.t04g05.controller.game.GameController;
import com.t04g05.controller.menu.MenuController;
import com.t04g05.gui.GUI;
import com.t04g05.gui.LanternaGUI;
import com.t04g05.model.menu.Menu;
import com.t04g05.states.GameState;
import com.t04g05.states.MenuState;

public class Main {
    public static void main(String[] args) {
        GUI gui = null;
        try {
            // Inicializa a GUI
            gui = new LanternaGUI();
            System.out.println("GUI inicializada.");

            // Inicializa o estado inicial (MenuState)
            GameState initialState = initializeMenu(gui);
            System.out.println("Estado inicial definido: " + initialState.getClass().getSimpleName());

            // Cria o controlador principal de estados
            GameController gameController = new GameController(initialState);

            // Inicia o loop principal do jogo
            gameController.process(gui);

            System.out.println("Jogo finalizado.");
        } catch (Exception e) {
            System.err.println("Erro durante a execução do jogo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (gui != null) {
                try {
                    gui.close();
                } catch (Exception e) {
                    System.err.println("Erro ao fechar a GUI: " + e.getMessage());
                }
            }
        }
    }

    private static GameState initializeMenu(GUI gui) {
        // Configura o menu e retorna o estado inicial
        Menu menu = new Menu();
        MenuController menuController = new MenuController(menu);
        return new MenuState(menuController, gui);
    }
}



package com.t04g05;

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
            gui = new LanternaGUI();
            System.out.println("GUI inicializada.");

            GameState currentState = initializeMenu(gui);
            System.out.println("Estado inicial definido: " + currentState.getClass().getSimpleName());
            currentState.run(gui);
            while (currentState != null) {
                System.out.println("Processando estado: " + currentState.getClass().getSimpleName());
                currentState.step(gui);
                currentState = currentState.getNextState();
                if (currentState == null) {
                    System.out.println("Nenhum próximo estado disponível. Encerrando o jogo.");
                } else {
                    System.out.println("Próximo estado: " + currentState.getClass().getSimpleName());
                }
            }
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
        // Criação do menu e controlador
        Menu menu = new Menu();
        MenuController menuController = new MenuController(menu);

        // Retorna um estado do menu
        return new MenuState(menuController, gui);
    }
}


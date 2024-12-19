package com.t04g05.states;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.arena.Arena3;
import com.t04g05.viewer.game.ArenaViewer;

import java.io.IOException;
public class Level3State extends GameState {
    private final ArenaController arenaController;
    public Level3State() {
        this.arenaController = new ArenaController(new Arena3());
    }
    @Override
    public void initializeLevel() {
        // Inicialização específica do nível 3 - falta implementar
        System.out.println("Inicializando o Nível 3...");
    }

    @Override
    public void step(GUI gui) throws IOException {
        gui.clear();
        ArenaViewer viewer = new ArenaViewer(arenaController.getArena());
        viewer.draw(gui);
        gui.refresh();

        arenaController.processInput(gui.getNextAction());
        arenaController.update();
    }

    @Override
    public void run(GUI gui) throws IOException {
        // Lógica do estado de execução, geralmente um loop de jogo
        while (!arenaController.isGameOver()) {
            step(gui); // Chama o step a cada ciclo
            try {
                Thread.sleep(100); // Para evitar um loop rápido demais
            } catch (InterruptedException e) {
                System.out.println("Jogo interrompido: " + e.getMessage());
            }
        }
    }

    @Override
    public Arena getArena() {
        return arenaController.getArena();
    }
}

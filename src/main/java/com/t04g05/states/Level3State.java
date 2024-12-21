package com.t04g05.states;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.arena.Arena2;
import com.t04g05.viewer.game.ArenaViewer;

import java.io.IOException;

public class Level3State extends GameState {
    private final ArenaController arenaController;
    private final ArenaViewer arenaViewer;

    public Level3State() {
        // Criação da arena específica do nível 2
        var arena = new Arena2();

        // Inicializa o controlador e o visualizador da arena
        this.arenaController = new ArenaController(arena);
        this.arenaViewer = new ArenaViewer(arena);
    }

    @Override
    public void step(GUI gui) {
        try {
            gui.clear();
            arenaViewer.draw(gui);
            gui.refresh();
            GUI.ACTION action = gui.getNextAction();
            System.out.println("Processando ação no Level2State: " + action);
            if (action != null) {
                arenaController.processInput(action);
            }
            arenaController.update();
            if (arenaController.isGoalReached()) {
                setNextState(new Level4State()); // Apenas quando o jogo realmente termina
            } else if (action==GUI.ACTION.QUIT) {
                setNextState(null); // Apenas quando o jogo realmente termina
            } else {
                setNextState(this); // Certifique-se de manter o estado atual
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O durante o processamento do Level2State: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public Arena getArena() {
        return arenaController.getArena(); // Retorna a arena do controlador
    }

}


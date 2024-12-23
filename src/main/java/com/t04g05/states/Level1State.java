package com.t04g05.states;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.controller.game.GameController;
import com.t04g05.controller.menu.GameOverController;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.arena.Arena1;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.menu.GameOver;
import com.t04g05.viewer.game.ArenaViewer;

import java.io.IOException;

public class Level1State extends GameState {
    private final ArenaController arenaController;
    private final ArenaViewer arenaViewer;

    public Level1State() {
        // Criação da arena específica do nível 1
        var arena = new Arena1();

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
            if (action != null) {
                arenaController.processInput(action);
            }
            arenaController.update();
            if (arenaController.isGoalReached()) {
                setNextState(new Level2State(arenaController.getArena().getCharacter()));
            } else if (arenaController.getArena().getCharacter().getLives() <= 0) {
                Character character = arenaController.getArena().getCharacter();
                GameOver gameover = new GameOver(character, character.getScore());
                GameOverController gameoverController = new GameOverController(gameover);
                setNextState(new GameOverState(character, gameoverController));
            } else if (action==GUI.ACTION.QUIT) {
                setNextState(null);
            } else {
                setNextState(this); // Certifique-se de manter o estado atual
            }

        } catch (IOException e) {
            System.err.println("Erro de I/O durante o processamento do Level1State: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public Arena getArena() {
        return arenaController.getArena(); // Retorna a arena do controlador
    }

}

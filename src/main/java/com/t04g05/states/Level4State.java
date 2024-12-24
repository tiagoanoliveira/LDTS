package com.t04g05.states;

import com.t04g05.controller.game.ArenaController;
import com.t04g05.controller.menu.GameOverController;
import com.t04g05.controller.menu.WinController;
import com.t04g05.gui.GUI;
import com.t04g05.model.Position;
import com.t04g05.model.game.arena.Arena;
import com.t04g05.model.game.arena.Arena4;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.menu.GameOver;
import com.t04g05.model.menu.Win;
import com.t04g05.viewer.game.ArenaViewer;

import java.io.IOException;

public class Level4State extends GameState {
    private final ArenaController arenaController;
    private final ArenaViewer arenaViewer;

    public Level4State(Character character) {
        var arena = new Arena4(character);

        arena.getCharacter().setPosition(new Position(4, 30));
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
                System.out.println("===================================");
                System.out.println("   VOCÊ COMPLETOU O ÚLTIMO NÍVEL!  ");
                System.out.println("===================================");
                Win win = new Win(arenaController.getArena().getCharacter().getScore());
                WinController winController = new WinController(win);
                setNextState(new WinState(win, winController));
            } else if (arenaController.getArena().getCharacter().getLives() <= 0) {
                Character character = arenaController.getArena().getCharacter();
                GameOver gameover = new GameOver(character, arenaController.getArena().getCharacter().getScore());
                GameOverController gameoverController = new GameOverController(gameover);
                setNextState(new GameOverState(character, gameoverController));
            } else if (action==GUI.ACTION.QUIT) {
                setNextState(null);
            } else {
                setNextState(this);
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O durante o processamento do Level4State: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public Arena getArena() {
        return arenaController.getArena();
    }

}


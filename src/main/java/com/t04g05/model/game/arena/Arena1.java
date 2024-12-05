package com.t04g05.model.game.arena;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.t04g05.model.game.elementos.Character;
import com.t04g05.model.game.elementos.ElementFactory;
import com.t04g05.model.Position;
import com.t04g05.model.game.elementos.Walls;
import com.t04g05.patterns.GameObserver;
import com.t04g05.patterns.GameSubject;
import com.t04g05.viewer.ScreenManager;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Arena1 implements GameObserver {
    private final Screen screen;
    private final Set<Walls> walls = new HashSet<>();
    private final Character character;
    private final Position goal;
    private final GameSubject gameSubject;

    public Arena1() {
        goal = new Position(45, 13);
        gameSubject = new GameSubject();
        gameSubject.addObserver(this);

        screen = ScreenManager.getInstance().getScreen();

        createMaze();
        character = new Character(54, 3);
    }

    private void createMaze() {
        //Paredes superiores e inferiores exteriores
        for (int i = 0; i < 60; i++) {
            walls.add((Walls) ElementFactory.createElement("Walls", i, 0));
            walls.add((Walls) ElementFactory.createElement("Walls", i, 30));
        }
        //Paredes laterais esquerda e direita exteriores
        for (int i = 0; i < 31; i++) {
            walls.add((Walls) ElementFactory.createElement("Walls", 0, i));
            walls.add((Walls) ElementFactory.createElement("Walls", 59, i));
        }
        //Paredes internas para o labirinto
        for (int i = 6; i < 16; i++) {
            walls.add((Walls) ElementFactory.createElement("Walls", 11, i)); // 1.ª linha vertical à esquerda
        }
        for (int i = 10; i < 26; i++) {
            walls.add((Walls) ElementFactory.createElement("Walls", 50, i)); // ultima linha vertical à direita
        }
        for (int i = 11; i < 60; i++) {
            walls.add((Walls) ElementFactory.createElement("Walls", i, 5)); // 1.ª linha horizontal em cima
        }
        for (int i = 20; i < 50; i++) {
            walls.add((Walls) ElementFactory.createElement("Walls", i, 10)); // 2º linha horizontal
        }
        for (int i = 11; i < 50; i++) {
            walls.add((Walls) ElementFactory.createElement("Walls", i, 15)); // 3º linha horizontal
        }
        for (int i = 1; i < 42; i++) {
            walls.add((Walls) ElementFactory.createElement("Walls", i, 20)); // 4º linha horizontal
        }
        for (int i = 10; i < 50; i++) {
            walls.add((Walls) ElementFactory.createElement("Walls", i, 25)); // ultima linha horizontal
        }
    }

    private void drawGoal(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(goal.getX(), goal.getY(), "O");
    }

    private void draw() throws IOException {
        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
        graphics.fill(' ');

        for (Walls wall : walls) {
            wall.draw(graphics);
        }

        character.draw(graphics);
        drawGoal(graphics);

        screen.refresh();
    }

    private boolean checkGoal() {
        if (character.getPosition().equals(goal)) {
            gameSubject.notifyGoalReached();
            return true;
        }
        return false;
    }



    public boolean run() {
        try {
            while (true) {
                draw();
                if (checkGoal()) {
                    return true;
                }

                KeyStroke key = screen.readInput();
                if (processInput(key)) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeScreen();
        }
        return false;
    }

    private boolean processInput(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            System.out.println("Exiting game...");
            return true;
        }
        character.processKey(key, walls, null);
        return false;
    }

    private void closeScreen() {
        ScreenManager.getInstance(true).stopScreen();
    }

    @Override
    public void onGoalReached() {
        System.out.println("Boa! Nível 1 completo!");
    }

    @Override
    public void onGameOver() {
        System.out.println("Game Over no Nível 1!");
    }
}
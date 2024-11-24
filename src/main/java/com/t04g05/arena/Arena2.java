package com.t04g05.arena;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.t04g05.characters.Character;
import com.t04g05.elements.ElementFactory;
import com.t04g05.elements.Obstacle;
import com.t04g05.elements.Position;
import com.t04g05.elements.Walls;
import com.t04g05.characters.Enemy;
import com.t04g05.patterns.GameObserver;
import com.t04g05.patterns.GameSubject;
import com.t04g05.screen.ScreenManager;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Arena2 implements GameObserver {
    private final Screen screen;
    private final Set<Walls> walls = new HashSet<>();
    private final Set<Obstacle> obstacles = new HashSet<>();
    private final Set<Enemy> enemies = new HashSet<>();
    private final Character character;
    private final Position goal;
    private final GameSubject gameSubject;

    public Arena2() {

        goal = new Position(45, 13);
        gameSubject = new GameSubject();
        gameSubject.addObserver(this);

        screen = ScreenManager.getInstance().getScreen();

        createMaze();
        createObstacles();
        spawnEnemies();
        character = new Character(54, 3);
    }

    private void createMaze() {
        // Paredes superior e inferior
        for (int i = 0; i < 60; i++) {
            walls.add(new Walls(i, 0));
            walls.add(new Walls(i, 30));
        }

        // Paredes laterais esquerda e direita
        for (int i = 0; i < 31; i++) {
            walls.add(new Walls(0, i));
            walls.add(new Walls(59, i));
        }

        // Paredes internas para o labirinto
        for (int i = 6; i <16 ; i++) {
            walls.add(new Walls(11, i)); // 1.ª linha vertical à esquerda
        }
        for (int i = 10; i < 26; i++) {
            walls.add(new Walls(50, i)); // ultima linha vertical à direita
        }
        for (int i = 11; i < 60; i++) {
            walls.add(new Walls(i, 5)); // 1.ª linha horizontal em cima
        }
        for (int i = 20; i < 50; i++) {
            walls.add(new Walls(i, 10)); // 2º linha horizontal
        }
        for (int i = 11; i < 50; i++) {
            walls.add(new Walls(i, 15)); // 3ª linha horizontal
        }
        for (int i = 1; i < 42; i++) {
            walls.add(new Walls(i, 20)); // 4ª linha horizontal
        }
        for (int i = 10; i < 50; i++) {
            walls.add(new Walls(i, 25)); // ultima linha horizontal
        }
    }

    private void createObstacles() {
        obstacles.add(new Obstacle(25, 12));
        obstacles.add(new Obstacle(25, 13));
        obstacles.add(new Obstacle(40, 18));
        obstacles.add(new Obstacle(40, 17));
        obstacles.add(new Obstacle(50, 7));
        obstacles.add(new Obstacle(50, 6));
        obstacles.add(new Obstacle(35, 22));
        obstacles.add(new Obstacle(35, 21));
    }
    private void spawnEnemies() {
        // Adicionar inimigos em posições específicas
        enemies.add((Enemy) ElementFactory.createElement("Enemy", 9, 10));
        enemies.add((Enemy) ElementFactory.createElement("Enemy", 42, 21));
        enemies.add((Enemy) ElementFactory.createElement("Enemy", 30, 4));
        enemies.add((Enemy) ElementFactory.createElement("Enemy", 30, 19));
        enemies.add((Enemy) ElementFactory.createElement("Enemy", 15, 27));
        enemies.add((Enemy) ElementFactory.createElement("Enemy", 52, 24));
        enemies.add((Enemy) ElementFactory.createElement("Enemy", 35, 12));
        enemies.add((Enemy) ElementFactory.createElement("Enemy", 40, 6));

    }

    private void drawGoal(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(goal.getX(), goal.getY(), "O");
    }

    private void drawObstacles(TextGraphics graphics) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(graphics);
        }
    }

    private void draw() throws IOException {
        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
        graphics.fill(' ');

        for (Walls wall : walls) {
            wall.draw(graphics);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(graphics);
        }
        drawObstacles(graphics);
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
        System.out.println("Iniciando o Nível 2...");

        try {
            while (true) {
                draw();
                if (checkGoal()) {
                    return true;
                }

                KeyStroke key = screen.readInput();
                if (processInput(key)) break;
                moveEnemies();
                if (checkEnemyCollision()) {
                    gameSubject.notifyGameOver();
                    break;
                }
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
        character.processKey(key, walls, obstacles);
        return false;
    }
    private void moveEnemies() {
        for (Enemy enemy : enemies) {
            enemy.move(walls);
        }
    }
    private boolean checkEnemyCollision() {
        for (Enemy enemy : enemies) {
            if (enemy.isCollidingWithCharacter(character)) {
                return true; // Colisão com inimigo
            }
        }
        return false;
    }
    private void closeScreen() {
        ScreenManager.getInstance(true).stopScreen();
    }

    @Override
    public void onGoalReached() {
        System.out.println("\n===================================");
        System.out.println("       YOU WIN - NÍVEL 2      ");
        System.out.println("===================================");
        System.out.println("     MUITO BEM, CONSEGUISTE!");
        System.out.println("===================================\n");
    }

    @Override
    public void onGameOver() {
        System.out.println("\n===================================");
        System.out.println("       GAME OVER - NÍVEL 2      ");
        System.out.println("===================================");
        System.out.println("   Foste apanhado por um inimigo!");
        System.out.println("===================================\n");

        try {
            Thread.sleep(1000); // Pausa de 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Encerrando o jogo...");
    }

}
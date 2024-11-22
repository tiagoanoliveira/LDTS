package com.t04g05;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Arena2 {
    private Screen screen;
    private Set<Walls> walls = new HashSet<>();
    private Set<Obstacle> obstacles = new HashSet<>();
    private Set<Enemy> enemies = new HashSet<>();
    private Character character;
    private Position goal;

    public Arena2() {
        goal = new Position(45, 13);
        try {
            TerminalSize terminalSize = new TerminalSize(60, 31);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            createMaze(); // Cria as paredes do labirinto
            createObstacles(); // Cria obstáculos
            createEnemies(); // Cria inimigos
            character = new Character(54, 3); // Inicializa o personagem
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            walls.add(new Walls(11, i)); // 1ª linha vertical à esquerda
        }
        for (int i = 10; i < 26; i++) {
            walls.add(new Walls(50, i)); // ultima linha vertical à direita
        }
        for (int i = 11; i < 60; i++) {
            walls.add(new Walls(i, 5)); // 1ª linha horizontal em cima
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

    private void createEnemies() {
        enemies.add(new Enemy(10, 10)); // Inimigo em posição inicial
        enemies.add(new Enemy(50, 15)); // Outro inimigo
    }

    private void drawGoal(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(goal.getX(), goal.getY(), "*");
    }

    private void draw() throws IOException {
        if (screen == null) return;
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
        graphics.fill(' ');
        // Desenhar paredes
        for (Walls wall : walls) {
            wall.draw(graphics);
        }
        // Desenhar obstáculos
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(graphics);
        }

        // Desenhar inimigos
        for (Enemy enemy : enemies) {
            enemy.draw(graphics);
        }

        // Desenhar o personagem
        character.draw(graphics);

        // Desenhar objetivo
        drawGoal(graphics);

        screen.refresh();
    }

    private boolean checkGoal() {
        return character.getPosition().equals(goal);
    }

    public void run() {
        if (screen == null) {
            System.out.println("Screen was not initialized. Exiting.");
            return;
        }

        try {
            while (true) {
                draw(); // Desenha o jogo
                if (checkGoal()) {
                    showWinMessage();
                    break;
                }

                for (Enemy enemy : enemies) {
                    enemy.move(walls); // Movimenta os inimigos
                    if (enemy.isCollidingWithCharacter(character)) {
                        showGameOverMessage();
                        return; // O jogo termina se o personagem colidir com um inimigo
                    }
                }


                KeyStroke key = screen.readInput();
                if (processInput(key)) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeScreen();
        }
    }

    private void showWinMessage() {
        System.out.println("Completaste o nível 2!");
    }

    private void showGameOverMessage() {
        System.out.println("Foste comido vivo! Fim de jogo.");
    }

    private boolean processInput(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            System.out.println("Exiting game...");
            return true;
        }
        character.processKey(key, walls, obstacles); // Move o personagem
        return false;
    }

    private void closeScreen() {
        try {
            if (screen != null) {
                screen.stopScreen();
                System.out.println("Screen stopped successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
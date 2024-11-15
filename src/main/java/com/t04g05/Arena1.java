package com.t04g05;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import java.io.IOException;

public class Arena1 {
    private Screen screen;
    private int x = 54; // Initial position for x, we can change this after
    private int y = 5; // Initial position for y
    private ArrayList<Walls> walls = new ArrayList<>();


    public Arena1() {
        try {
            TerminalSize terminalSize = new TerminalSize(60, 30);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            createMaze();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Metodo para definir as paredes do labirinto
    private void createMaze() {
        // Paredes superior e inferior
        for (int i = 0; i < 60; i++) {
            walls.add(new Walls(i, 0));
            walls.add(new Walls(i, 29));
        }

        // Paredes laterais esquerda e direita
        for (int i = 0; i < 30; i++) {
            walls.add(new Walls(0, i));
            walls.add(new Walls(59, i));
        }

        // Paredes internas para o labirinto
        for (int i = 15; i < 60; i++) {
            walls.add(new Walls(i, 10)); // linha horizontal no meio
        }
        for (int i = 10; i < 20; i++) {
            walls.add(new Walls(15, i)); // linha vertical à esquerda
        }
        for (int i = 15; i < 45; i++) {
            walls.add(new Walls(i, 20)); // linha horizontal embaixo
        }
    }

    private void draw() throws IOException {
        if (screen == null) return;
        screen.clear();

        // Configurar fundo castanho para todo o mapa
        TextGraphics graphics = screen.newTextGraphics();

        // Preencher o fundo com castanho
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
        graphics.fill(' ');

        for (Walls wall : walls) {
            wall.draw(graphics);
        }
        //Isto tem que ser alterado depois de criarmos o Character
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);

        // Coloquei um "O" no final do labirinto
        screen.setCharacter(20, 15, TextCharacter.fromCharacter('O')[0]);

        screen.refresh();
    }

    private boolean isWall(int x, int y) {
        for (Walls wall : walls) {
            if (wall.getPosition().getX() == x && wall.getPosition().getY() == y) {
                return true;
            }
        }
        return false;
    }

    private void processKey(KeyStroke key) {
        int newX = x;
        int newY = y;

        switch (key.getKeyType()) {
            case ArrowUp -> y = Math.max(1, newY - 1);
            case ArrowDown -> y = Math.min(28, newY + 1);
            case ArrowLeft -> x = Math.max(1, newX - 1);
            case ArrowRight -> x = Math.min(58, newX + 1);
        }
        if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();
            switch (character) {
                case 'W', 'w' -> y = Math.max(1, newY - 1);
                case 'S', 's' -> y = Math.min(28, newY + 1);
                case 'A', 'a' -> x = Math.max(1, newX - 1);
                case 'D', 'd' -> x = Math.min(58, newX + 1);
            }
        }


        if (!isWall(newX, newY)) {
            System.out.println("Movendo para: (" + newX + ", " + newY + ")");
            x = newX;
            y = newY;
        } else {
            System.out.println("Movimento bloqueado por parede.");
        }
    }

    public void run() {
        if (screen == null) {
            System.out.println("Screen was not initialized. Exiting.");
            return;
        }

        try {
            while (true) {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    System.out.println("Exiting game...");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
}

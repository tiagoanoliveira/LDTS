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


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Arena1 {
    private Screen screen;
    private Set<Walls> walls = new HashSet<>(); // HashSet para paredes
    private Character character;
    private Position goal;

    public Arena1() {
        goal = new Position(20, 15);
        try {
            TerminalSize terminalSize = new TerminalSize(60, 30);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            createMaze(); // Cria as paredes do labirinto
            character = new Character(54, 5); // Inicializa o personagem
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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

    // Desenhar o jogo
    private void draw() throws IOException {
        if (screen == null) return;
        screen.clear();

        // Configurar fundo castanho para todo o mapa
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
        graphics.fill(' ');

        // Desenhar as paredes
        for (Walls wall : walls) {
            wall.draw(graphics);
        }

        // Desenhar o personagem
        character.draw(graphics);

        // Colocar um objetivo no final do labirinto
        screen.setCharacter(20, 15, TextCharacter.fromCharacter('O')[0]);

        screen.refresh();
    }
    private boolean checkGoal(){
        return character.getPosition().equals(goal);
    }

    // Loop principal do jogo
    public void run() {
        if (screen == null) {
            System.out.println("Screen was not initialized. Exiting.");
            return;
        }

        try {
            while (true) {
                draw();
                if (checkGoal()) {
                    System.out.println("Parabéns! Você alcançou o objetivo!");
                    break;
                }
                KeyStroke key = screen.readInput(); // Lê entrada do usuário
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    System.out.println("Exiting game...");
                    break;
                }
                // Delegar o movimento ao personagem, passando as paredes
                character.processKey(key, walls); // Aqui as paredes são passadas corretamente
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

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

public class Arena1 {
    private Screen screen;
    private Set<Walls> walls = new HashSet<>(); // HashSet para paredes
    private Character character;
    private Position goal;

    public Arena1() {
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
            character = new Character(54, 3); // Inicializa o personagem
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Construção do labirinto definindo as paredes exteriores e interiores
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

    // desenhar o goal no final
    private void drawGoal(TextGraphics graphics) {
        // Definir a cor de fundo e a cor do texto
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C")); // Fundo castanho
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF")); // Letra branca
        graphics.putString(goal.getX(), goal.getY(), "O");
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
        drawGoal(graphics);

        screen.refresh();
    }
    private boolean checkGoal() {
        return character.getPosition().equals(goal); // Compara com a posição correta do objetivo
    }
    public void run() {
        if (screen == null) {
            System.out.println("Screen was not initialized. Exiting.");
            return;
        }

        try {
            while (true) {
                draw(); // Desenha o jogo
                if (checkGoal()) { // Verifica se o objetivo foi alcançado
                    showWinMessage();
                    break;
                }

                KeyStroke key = screen.readInput(); // Lê entrada do utilizador
                if (processInput(key)) break; // Processa e verifica se deve sair do jogo
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeScreen();
        }
    }

    // Mostra uma mensagem de vitória
    private void showWinMessage() {
        System.out.println("É isso mesmo, nível 1 concluído!");
    }

    // Processa entrada e verifica se o jogo deve terminar
    private boolean processInput(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            System.out.println("Exiting game...");
            return true;
        }
        character.processKey(key, walls); // Move o personagem
        return false;
    }

    // Fecha o ecrã corretamente
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

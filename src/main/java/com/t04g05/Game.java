package com.t04g05;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.IOException;

public class Game {
    private Screen screen;
    private int x = 10; // Initial position for x, we can change this after
    private int y = 10; // Initial position for y

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            System.out.println("Screen initialized successfully.");
        } catch (IOException e) {
            System.out.println("Failed to initialize screen.");
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        if (screen == null) return; // Ensure screen is initialized before drawing
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        System.out.println("Key pressed: " + key);
        // Update x and y based on arrow keys or w,a,s,d
        switch (key.getKeyType()) {
            case ArrowUp -> y = Math.max(0, y - 1);
            case ArrowDown -> y = Math.min(19, y + 1);
            case ArrowLeft -> x = Math.max(0, x - 1);
            case ArrowRight -> x = Math.min(39, x + 1);
        }
        if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();
            switch (character) {
                case 'W', 'w' -> y = Math.max(0, y - 1);
                case 'S', 's' -> y = Math.min(19, y + 1);
                case 'A', 'a' -> x = Math.max(0, x - 1);
                case 'D', 'd' -> x = Math.min(39, x + 1);
            }
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

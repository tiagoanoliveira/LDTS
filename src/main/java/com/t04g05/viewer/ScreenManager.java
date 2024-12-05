package com.t04g05.viewer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ScreenManager {
    private static ScreenManager instance;
    private Screen screen;

    private ScreenManager() {
        initScreen();
    }

    private void initScreen() {
        try {
            Terminal terminal = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(60, 31))
                    .createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.screen.startScreen();
        } catch (IOException e) {
            System.err.println("Erro ao inicializar o ecrã: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ScreenManager getInstance() {
        return getInstance(false);
    }

    public static ScreenManager getInstance(boolean reset) {
        if (instance == null || reset) {
            if (reset && instance != null) {
                instance.stopScreen(); // Parar o screen antigo antes de criar um novo
            }
            instance = new ScreenManager();
        }
        return instance;
    }


    public Screen getScreen() {
        return screen;
    }

    public void stopScreen() {
        try {
            if (screen != null) {
                screen.stopScreen();
            }
        } catch (IOException e) {
            System.err.println("Erro ao parar o ecrã: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
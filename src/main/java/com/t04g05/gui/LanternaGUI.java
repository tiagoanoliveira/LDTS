package com.t04g05.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.t04g05.model.Position;

import java.io.IOException;

public class LanternaGUI implements GUI {
    private final Terminal terminal;
    private final TerminalScreen screen;
    private final TextGraphics textGraphics;

    public LanternaGUI() throws IOException {
        TerminalSize terminalSize = new TerminalSize(60, 31);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        this.terminal = terminalFactory.createTerminal();
        this.screen = new TerminalScreen(terminal);
        this.screen.startScreen();
        this.screen.setCursorPosition(null);
        this.textGraphics = screen.newTextGraphics();
    }

    @Override
    public void drawText(int x, int y, String text, String color) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString(color));
        textGraphics.putString(x, y, text);
    }

    @Override
    public void drawElement(Position position, char character, String textColor, String backgroundColor) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString(textColor));
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        textGraphics.putString(position.getX(), position.getY(), String.valueOf(character));
    }


    @Override
    public void clear() {
        screen.clear();
    }


    @Override
    public void refresh() {
        try {
            screen.refresh(); // Atualiza a tela
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ACTION getNextAction() {
        try {
            KeyStroke keyStroke = screen.readInput(); // Aguarda entrada do usuário
            if (keyStroke == null) {
                return ACTION.NONE; // Retorna uma ação padrão que não faz nada
            }

            switch (keyStroke.getKeyType()) {
                case ArrowUp:
                    return ACTION.UP;
                case ArrowDown:
                    return ACTION.DOWN;
                case ArrowLeft:
                    return ACTION.LEFT;
                case ArrowRight:
                    return ACTION.RIGHT;
                case Enter:
                    return ACTION.ENTER;
                case Escape:
                    return ACTION.ESC;
                default:
                    return ACTION.NONE; // Ação padrão para teclas não mapeadas
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ACTION.ESC; // Retorna ESC em caso de erro, como fallback
        }
    }


    @Override
    public void close() {
        try {
            screen.close(); // Fecha a tela corretamente
            terminal.close(); // Fecha o terminal
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
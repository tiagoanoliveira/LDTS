package com.t04g05.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.t04g05.controller.game.ArenaController;
import com.t04g05.model.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
            System.out.println("Tecla capturada: " + keyStroke);
            if (keyStroke == null) {
                return ACTION.NONE; // Retorna uma ação padrão que não faz nada
            }
            switch (keyStroke.getKeyType()) {
                case ArrowUp: return ACTION.UP;
                case ArrowDown: return ACTION.DOWN;
                case ArrowLeft: return ACTION.LEFT;
                case ArrowRight: return ACTION.RIGHT;
                case Enter: return ACTION.ENTER;
                case Escape: return ACTION.ESC;
            }
            if (keyStroke.getCharacter() != null) {
                switch (keyStroke.getCharacter()) {
                    case 'W', 'w': return ACTION.UP;
                    case 'S', 's': return ACTION.DOWN;
                    case 'A', 'a': return ACTION.LEFT;
                    case 'D', 'd': return ACTION.RIGHT;
                    case 'Q', 'q': return ACTION.QUIT; // Adicione esta linha
                    default: return ACTION.NONE;
                }
            }
            return ACTION.NONE;
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

    @Override
    public void drawWall(Position position) {
        drawElement(position, '#', "#3333FF", null);
    }

    @Override
    public void drawEnemy(Position position) {
        drawElement(position, '@', "#CC0000", null);
    }

    @Override
    public void drawCoin(Position position)  {
        drawElement(position, '$', "#FFC222", null);
    }

    //sprite para personagem principal
    @Override
    public void drawCharacter(Position position) throws IOException {
        BufferedImage hero = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("sprites/hero.png")));

        for (int x = 0; x < hero.getWidth(); x++){
            for (int y = 0; y < hero.getHeight(); y++){
                int a = hero.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = a & 255;

                if (alpha != 0) {
                    TextCharacter c = new TextCharacter(' ', new TextColor.RGB(red, green, blue), new TextColor.RGB(red, green, blue));
                    textGraphics.setCharacter(position.getX() + x, position.getY() + y, c);
                }
            }
        }
    }
}
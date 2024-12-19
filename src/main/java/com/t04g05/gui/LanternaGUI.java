package com.t04g05.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.t04g05.model.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

    @Override
    public void drawWall(Position position) {
        drawElement(position, '#', "#3333FF", "000000");
    }

    @Override
    public void drawEnemy(Position position) {
        drawElement(position, '@', "#CC0000", "#000000");
    }

    @Override
    public void drawCoin(Position position)  {
        drawElement(position, '$', "#FFC222", "#000000");
    }

    //sprite para personagem principal
    @Override
    public void drawCharacter(Position position) throws IllegalArgumentException {
        try {
            // Load the image
            BufferedImage hero = ImageIO.read(getClass().getResource("sprites/hero.png"));
            if (hero == null) {
                throw new IllegalArgumentException("Failed to load sprite image.");
            }

            // Check if textGraphics is null
            if (textGraphics == null) {
                throw new IllegalArgumentException("TextGraphics is null.");
            }

            // Iterate through each pixel in the image
            for (int x = 0; x < hero.getWidth(); x++) {
                for (int y = 0; y < hero.getHeight(); y++) {
                    int pixelColor = hero.getRGB(x, y);
                    int alpha = (pixelColor >> 24) & 0xff;
                    int red = (pixelColor >> 16) & 0xff;
                    int green = (pixelColor >> 8) & 0xff;
                    int blue = pixelColor & 0xff;

                    // Only render non-transparent pixels
                    if (alpha != 0) {
                        TextCharacter c = new TextCharacter(' ',
                                new TextColor.RGB(red, green, blue),
                                new TextColor.RGB(red, green, blue));
                        textGraphics.setCharacter(position.getX() + x, position.getY() + y, c);
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading sprite image.", e);
        }
    }

}
package com.t04g05.gui;

import com.t04g05.model.Position;

import java.io.IOException;

public interface GUI {
    void drawText(int x, int y, String text, String color);
    void drawElement(Position position, char character, String textColor, String backgroundColor);
    void clear();
    void refresh() throws IOException;
    ACTION getNextAction();
    void close() throws IOException;
    void drawCharacter(Position position) throws IOException;
    void drawWall(Position position);
    void drawEnemy(Position position);
    void drawCoin(Position position) throws IOException;
    void drawDoor(Position position) throws IOException;
    enum ACTION {
        UP, DOWN, LEFT, RIGHT, ENTER, ESC, NONE, QUIT
    }
}
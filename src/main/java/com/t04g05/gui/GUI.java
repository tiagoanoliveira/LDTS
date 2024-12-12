package com.t04g05.gui;

import com.t04g05.model.Position;

import java.io.IOException;

public interface GUI {
    void drawText(int x, int y, String text, String color);
    void drawElement(Position position, char character, String textColor, String backgroundColor);
    void clear() throws IOException;
    void refresh();
    ACTION getNextAction();
    void close();


    enum ACTION {
        UP, DOWN, LEFT, RIGHT, ENTER, ESC, NONE
    }
}
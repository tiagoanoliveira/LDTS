package com.t04g05;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Obstacle extends Element {
    public Obstacle(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF6347")); // Cor do obstáculo (laranja)
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "#");
    }
}

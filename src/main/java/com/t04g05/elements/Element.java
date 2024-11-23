package com.t04g05.elements;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private Position position = null;

    public Element(int x, int y) {
        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public abstract void draw(TextGraphics graphics);
}

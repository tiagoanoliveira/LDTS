package com.t04g05;
import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Element {
    private Position position;

    public Element(int x, int y) {
        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void draw(TextGraphics screen);
}

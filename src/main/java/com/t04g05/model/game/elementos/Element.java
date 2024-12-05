package com.t04g05.model.game.elementos;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.t04g05.model.Position;

public abstract class Element {
    protected Position position = null;

    public Element(int x, int y) {
        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public abstract void draw(TextGraphics graphics);
}

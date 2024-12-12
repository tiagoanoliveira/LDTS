package com.t04g05.model.game.elements;

import com.t04g05.model.Position;

public abstract class Element implements GameElement {
    private Position position;

    public Element(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

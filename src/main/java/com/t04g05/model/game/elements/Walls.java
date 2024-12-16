package com.t04g05.model.game.elements;

import com.t04g05.model.Position;

public class Walls extends Element {
    public Walls(Position position) {
        super(position.getX(), position.getY());
    }
}
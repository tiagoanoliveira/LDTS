package com.t04g05.model.game.elementos;

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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Obstacle obstacle = (Obstacle) obj;
        return position.equals(obstacle.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }


}

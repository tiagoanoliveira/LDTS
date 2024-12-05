package com.t04g05.model.game.elementos;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.t04g05.patterns.MovementStrategy;
import com.t04g05.patterns.NormalMovement;
import com.t04g05.model.Position;


import java.util.Set;

public class Character {
    private Position position;
    private final MovementStrategy movementStrategy;

    public Character(int x, int y) {
        this.position = new Position(x, y);
        this.movementStrategy = new NormalMovement(); // Estratégia padrão
    }

    public Position getPosition() {
        return position;
    }

    public void processKey(KeyStroke key, Set<Walls> walls, Set<Obstacle> obstacles) {
        movementStrategy.move(this, key, walls, obstacles);
    }


    public void moveTo(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6E522C"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.putString(position.getX(), position.getY(), "X");
    }
}

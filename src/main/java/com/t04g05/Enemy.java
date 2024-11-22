package com.t04g05;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Set;

public class Enemy extends Element {
    private int directionX;
    private int directionY;

    public Enemy(int x, int y) {
        super(x, y);
        // Direção de movimento aleatória (simples)
        this.directionX = (Math.random() > 0.5) ? 1 : -1;
        this.directionY = (Math.random() > 0.5) ? 1 : -1;
    }

    public void move(Set<Walls> walls) {
        int newX = getPosition().getX() + directionX;
        int newY = getPosition().getY() + directionY;

        // Impede o inimigo de atravessar paredes
        if (!walls.contains(new Walls(newX, newY))) {
            getPosition().setX(newX);
            getPosition().setY(newY);
        } else {
            // Inverte a direção se encontrar uma parede
            directionX *= -1;
            directionY *= -1;
        }
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000")); // Cor do inimigo (vermelho)
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "E");
    }

    public boolean isCollidingWithCharacter(Character character) {
        return character.getPosition().equals(getPosition());
    }
}

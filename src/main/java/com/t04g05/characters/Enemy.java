package com.t04g05.characters;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t04g05.elements.Element;
import com.t04g05.elements.Position;
import com.t04g05.elements.Walls;
import com.t04g05.patterns.MovementStrategy;
import com.t04g05.patterns.RandomMovement;
import java.util.Set;

public class Enemy extends Element {
    private final MovementStrategy movementStrategy;

    public Enemy(int x, int y) {
        super(x, y);
        this.movementStrategy = new RandomMovement(); // Estratégia de movimento aleatório
    }

    public void move(Set<Walls> walls) {
        movementStrategy.move(this, walls, null); // Chama o move() com a estratégia de movimento aleatório
    }
    public void moveTo(Position position) {
        this.position = position; // Atualiza a posição do inimigo
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000")); // Cor do inimigo (vermelho)
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "E"); //Depois podemos alterar o E para um "emoji" de um ghost
    }

    public boolean isCollidingWithCharacter(Character character) {
        return character.getPosition().equals(getPosition());
    }
}


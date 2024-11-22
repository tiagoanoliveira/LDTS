package com.t04g05;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.TextColor;
import java.util.Set;

public class Character {
    private Position position;

    public Character(int x, int y){
        position = new Position(x,y);
    }

    public Position getPosition(){
        return position;
    }

    public void processKey(KeyStroke key, Set<Walls> walls, Set<Obstacle> obstacles) {
        int newX = position.getX();
        int newY = position.getY();

        switch (key.getKeyType()) {
            case ArrowUp -> newY = Math.max(1, newY - 1);
            case ArrowDown -> newY = Math.min(29, newY + 1);
            case ArrowLeft -> newX = Math.max(1, newX - 1);
            case ArrowRight -> newX = Math.min(59, newX + 1);
        }
        if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();
            switch (character) {
                case 'W', 'w' -> newY = Math.max(1, newY - 1);
                case 'S', 's' -> newY = Math.min(29, newY + 1);
                case 'A', 'a' -> newX = Math.max(1, newX - 1);
                case 'D', 'd' -> newX = Math.min(59, newX + 1);
            }
        }

        // Verificar se o movimento não colide com as paredes
        if (walls.contains(new Walls(newX, newY))) {
            System.out.println("Movimento bloqueado por parede.");
        }
        // Verificar se o movimento não colide com obstáculos ou inimigos (Arena 2)
        else if (obstacles != null && obstacles.contains(new Obstacle(newX, newY))) {
            System.out.println("Movimento bloqueado por obstáculo.");
        }

        // Se não houver colisão com paredes ou obstáculos, permite o movimento
        else {
            position.setX(newX);
            position.setY(newY);
        }

    }
    public boolean isCollidingWithEnemy(Enemy enemy) {
        return position.equals(enemy.getPosition());
    }


    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}
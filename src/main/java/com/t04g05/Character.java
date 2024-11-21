package com.t04g05;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.TextColor;
import java.io.IOException;
import java.util.Set;

public class Character {
    private Position position;

    public Character(int x, int y){
        this.position = new Position(x,y);
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public void processKey(KeyStroke key, Set<Walls> walls) {
        int newX = position.getX();
        int newY = position.getY();

        switch (key.getKeyType()) {
            case ArrowUp -> newY = Math.max(1, newY - 1);
            case ArrowDown -> newY = Math.min(28, newY + 1);
            case ArrowLeft -> newX = Math.max(1, newX - 1);
            case ArrowRight -> newX = Math.min(58, newX + 1);
        }
        if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();
            switch (character) {
                case 'W', 'w' -> newY = Math.max(1, newY - 1);
                case 'S', 's' -> newY = Math.min(28, newY + 1);
                case 'A', 'a' -> newX = Math.max(1, newX - 1);
                case 'D', 'd' -> newX = Math.min(58, newX + 1);
            }
        }

        if (!walls.contains(new Walls(newX, newY))) {
            position.setX(newX);
            position.setY(newY);
        } else {
            System.out.println("Movimento bloqueado por parede.");
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}

package com.t04g05;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
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

public class Wall extends Element {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#333366")); // Cor cinza-escuro
        screen.setBackgroundColor(TextColor.Factory.fromString("#A0522D")); // Cor de fundo castanha
        screen.enableModifiers(SGR.BOLD); // Negrito
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "#");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;

        return (this == o ||
                this.getPosition().equals(((Wall) o).getPosition()));
    }
}

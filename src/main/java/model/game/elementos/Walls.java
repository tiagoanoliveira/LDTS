package model.game.elementos;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Walls extends Element {
    public Walls(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#333366")); // Cor azul-escuro
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "#"); // Representa a parede como "#"
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Walls wall = (Walls) obj;
        return position.equals(wall.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }

}

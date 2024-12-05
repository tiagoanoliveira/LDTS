package com.t04g05.model.game.elementos;

public class ElementFactory {
    public static Element createElement(String type, int x, int y) {
        return switch (type) {
            case "Walls" -> new Walls(x, y);
            case "Obstacle" -> new Obstacle(x, y);
            case "Enemy" -> new Enemy(x, y);
            default -> throw new IllegalArgumentException("Tipo de elemento desconhecido: " + type);
        };
    }
}

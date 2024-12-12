package com.t04g05.controller.game;

import com.googlecode.lanterna.input.KeyType;
import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.Character;
import com.t04g05.model.game.elements.Walls;
import com.t04g05.model.game.elements.Obstacle;
import com.t04g05.viewer.game.CharacterViewer;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.Set;

public class CharacterController {
    private Character character;
    private Set<Walls> walls;
    private Set<Obstacle> obstacles;
    private CharacterViewer characterView;
    private final GUI gui;

    public CharacterController(Character character, Set<Walls> walls, Set<Obstacle> obstacles, GUI gui) {
        this.character = character;
        this.walls = walls;
        this.obstacles = obstacles;
        this.characterView = new CharacterViewer();
        this.gui = gui;
    }

    public void moveCharacter(KeyStroke keyStroke) {
        int newX = character.getPosition().getX();
        int newY = character.getPosition().getY();

        switch (keyStroke.getKeyType()) {
            case ArrowUp -> newY = Math.max(0, newY - 1);
            case ArrowDown -> newY = Math.min(30, newY + 1);
            case ArrowLeft -> newX = Math.max(0, newX - 1);
            case ArrowRight -> newX = Math.min(59, newX + 1);
        }
        if (keyStroke.getKeyType() == KeyType.Character) {
            char characters = keyStroke.getCharacter();
            switch (characters) {
                case 'W', 'w' -> newY = Math.max(0, newY - 1);
                case 'S', 's' -> newY = Math.min(30, newY + 1);
                case 'A', 'a' -> newX = Math.max(0, newX - 1);
                case 'D', 'd' -> newX = Math.min(59, newX + 1);
            }
        }
        if (isMoveValid(newX, newY)) {
            character.getPosition().setX(newX);
            character.getPosition().setY(newY);
        }
        characterView.draw(gui, character);
    }

    private boolean isMoveValid(int newX, int newY) {
        // Verifica se o novo movimento colide com uma parede ou um obstáculo
        for (Walls wall : walls) {
            if (wall.getPosition().getX() == newX && wall.getPosition().getY() == newY) {
                return false; // Colidiu com parede
            }
        }
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getPosition().getX() == newX && obstacle.getPosition().getY() == newY) {
                return false; // Colidiu com obstáculo
            }
        }
        return true; // Movimento válido
    }
}

package com.t04g05.patterns;

import model.game.elementos.Character;
import model.game.elementos.Enemy;
import model.game.elementos.Obstacle;
import model.game.elementos.Walls;
import java.util.Set;
import com.googlecode.lanterna.input.KeyStroke;

public interface MovementStrategy {
    void move(Character character, KeyStroke keyStroke, Set<Walls> walls, Set<Obstacle> obstacles);
    void move(Enemy enemy, Set<Walls> walls, Set<Obstacle> obstacles);
}


package com.t04g05.patterns;

import com.t04g05.characters.Character;
import com.t04g05.characters.Enemy;
import com.t04g05.elements.Obstacle;
import com.t04g05.elements.Walls;
import java.util.Set;
import com.googlecode.lanterna.input.KeyStroke;

public interface MovementStrategy {
    void move(Character character, KeyStroke keyStroke, Set<Walls> walls, Set<Obstacle> obstacles);
    void move(Enemy enemy, Set<Walls> walls, Set<Obstacle> obstacles);
}


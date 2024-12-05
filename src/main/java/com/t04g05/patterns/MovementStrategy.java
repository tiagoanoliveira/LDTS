package com.t04g05.patterns;

import com.t04g05.model.game.elementos.Character;
import com.t04g05.model.game.elementos.Enemy;
import com.t04g05.model.game.elementos.Obstacle;
import com.t04g05.model.game.elementos.Walls;
import java.util.Set;
import com.googlecode.lanterna.input.KeyStroke;

public interface MovementStrategy {
    void move(Character character, KeyStroke keyStroke, Set<Walls> walls, Set<Obstacle> obstacles);
    void move(Enemy enemy, Set<Walls> walls, Set<Obstacle> obstacles);
}


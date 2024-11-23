package com.t04g05.patterns;

import com.googlecode.lanterna.input.KeyStroke;
import com.t04g05.characters.Character;
import com.t04g05.elements.Walls;
import com.t04g05.elements.Obstacle;
import java.util.Set;

public interface MovementStrategy {
    void move(Character character, KeyStroke keyStroke, Set<Walls> walls, Set<Obstacle> obstacles);
}

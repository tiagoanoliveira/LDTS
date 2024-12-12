package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.GameElement;
import com.t04g05.model.game.elements.Character;

public class CharacterViewer {
    public void draw(GUI gui, GameElement element) {
        if (element instanceof Character) {
            Character character = (Character) element;
            gui.drawElement(character.getPosition(), 'X', "#FFFF33", null);
        }
    }
}

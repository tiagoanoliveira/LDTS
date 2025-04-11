package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.Character;

import java.io.IOException;

public class CharacterViewer implements ElementViewer<Character>{
    @Override
    public void draw(GUI gui, Character character) throws IOException {
        gui.drawCharacter(character.getPosition());
    }
}

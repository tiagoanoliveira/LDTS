package com.t04g05.viewer.game;

import com.t04g05.gui.GUI;
import com.t04g05.model.game.elements.Element;

public interface ElementViewer<T extends Element> {
    void draw(GUI gui, T element);
}
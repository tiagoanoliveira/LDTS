package com.t04g05.viewer.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Menu;

public class MenuViewer {
    private final Menu menu;

    public MenuViewer(Menu menu) {
        this.menu = menu;
    }

    public void draw(GUI gui) {
        gui.drawText(5, 5, "HEROMAN AND THE", "#FFFFFF");
        gui.drawText(5, 6, "DUNGEONS OF DISGRACE", "#FFFFFF");
        for (int i = 0; i < menu.getOptions().size(); i++) {
            String color = menu.getCurrentOption() == i ? "#FFFF00" : "#FFFFFF";
            gui.drawText(5, 9 + i, menu.getOptions().get(i), color);
        }
    }
}

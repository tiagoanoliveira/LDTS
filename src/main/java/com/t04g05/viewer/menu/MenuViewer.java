package com.t04g05.viewer.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Menu;

public class MenuViewer {
    private final Menu menu;

    public MenuViewer(Menu menu) {
        this.menu = menu;
    }

    public void draw(GUI gui) {
        if (menu.getMode() == Menu.Mode.MAIN_MENU) {
            gui.drawText(35, 19, "HEROMAN AND THE", "#FFFFFF");
            gui.drawText(35, 20, "DUNGEONS OF DISGRACE", "#FFFFFF");
        } else if (menu.getMode() == Menu.Mode.INSTRUCTIONS) {
            gui.drawText(20, 15, "INSTRUCTIONS", "#FFFFFF");
            gui.drawText(20, 18, "Controls:", "#FFFFFF");
            gui.drawText(20, 19, "UP, DOWN, LEFT, RIGHT", "#FFFFFF");
            gui.drawText(20, 21, "Goal:", "#FFFFFF");
            gui.drawText(20, 22, "Find the door in each level, avoiding the", "#FFFFFF");
            gui.drawText(20, 23, "enemies and collecting the coins along the way.", "#FFFFFF");
        }

        for (int i = 0; i < menu.getCurrentOptions().size(); i++) {
            String color = menu.getCurrentOption() == i ? "#FFFF00" : "#FFFFFF";
            gui.drawText(38, 25 + i, menu.getCurrentOptions().get(i), color);
        }
    }
}

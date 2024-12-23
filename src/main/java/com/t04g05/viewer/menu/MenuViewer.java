package com.t04g05.viewer.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Menu;

public class MenuViewer {
    private final Menu menu;

    public MenuViewer(Menu menu) {
        this.menu = menu;
    }

    public void draw(GUI gui) {
        String background_color = "#000000";
        if (menu.getMode() == Menu.Mode.MAIN_MENU) {
            gui.drawText(35, 19, "HEROMAN AND THE", "#FFFFFF", background_color);
            gui.drawText(35, 20, "DUNGEONS OF DISGRACE", "#FFFFFF", background_color);
        } else if (menu.getMode() == Menu.Mode.INSTRUCTIONS) {
            gui.drawText(20, 7, "=============== INSTRUCTIONS ================", "#FFFFFF", background_color);
            gui.drawText(20, 10, "Controls:", "#FFFFFF", background_color);
            gui.drawText(20, 11, "UP, DOWN, LEFT, RIGHT (or WSAD)", "#FFFFFF", background_color);
            gui.drawText(20, 13, "Goal:", "#FFFFFF", background_color);
            gui.drawText(20, 14, "Find the door in each level, avoiding the", "#FFFFFF", background_color);
            gui.drawText(20, 15, "enemies and collecting the coins along the way.", "#FFFFFF", background_color);
            gui.drawText(20, 17, "Rules:", "#FFFFFF", background_color);
            gui.drawText(20, 18, "You have 3 lives. Each time you collide into a monster", "#FFFFFF", background_color);
            gui.drawText(20, 19, "you lose one of them. When you don't have a single", "#FFFFFF", background_color);
            gui.drawText(20, 20, "life left, it's Game Over!", "#FFFFFF", background_color);
            gui.drawText(20, 22, "Disclaimer:", "#FFFFFF", background_color);
            gui.drawText(20, 23, "Whenever you want to quit the game, press 'Q'.", "#FFFFFF", background_color);
        }

        for (int i = 0; i < menu.getCurrentOptions().size(); i++) {
            String color = menu.getCurrentOption() == i ? "#FFFF00" : "#FFFFFF";
            gui.drawText(38, 25 + i, menu.getCurrentOptions().get(i), color, background_color);
        }
    }
}

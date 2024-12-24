package com.t04g05.viewer.menu;

import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Win;

public class WinViewer {
    private final Win win;

    public WinViewer(Win win) {
        this.win = win;
    }

    public void draw(GUI gui) {
        gui.drawText(30, 19, "                                      ", "#000", "#00FF00");
        gui.drawText(30, 20, " ==================================== ", "#000", "#00FF00");
        gui.drawText(30, 21, "                                      ", "#000", "#00FF00");
        gui.drawText(30, 22, "          PARABÉNS! GANHASTE!         ", "#000", "#00FF00");
        gui.drawText(30, 23, "                                      ", "#000", "#00FF00");
        gui.drawText(30, 24, " ==================================== ", "#000", "#00FF00");
        gui.drawText(30, 25, "                                      ", "#000", "#00FF00");
        gui.drawText(30, 26, "          Total Score: " + win.score() + "          ", "#000", "#00FF00");
        gui.drawText(30, 27, "                                      ", "#000", "#00FF00");
        gui.drawText(30, 28, "       Pressione [Q] para sair        ", "#000", "#00FF00");
        gui.drawText(30, 29, "                                      ", "#000", "#00FF00");
    }
}

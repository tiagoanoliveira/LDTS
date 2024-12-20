package com.t04g05.viewer.menu;
import com.t04g05.gui.GUI;
import com.t04g05.model.menu.Instructions;

public class InstructionsViewer {
    private final Instructions instructions;
    public InstructionsViewer(Instructions instructions){ this.instructions = instructions;}
    public void draw(GUI gui) {
        gui.drawText(5, 5, "INSTRUCTIONS", "#FFFFFF");
        gui.drawText(5, 8, "Controls:", "#FFFFFF");
        gui.drawText(5, 9, "UP, DOWN, LEFT, RIGHT", "#FFFFFF");
        gui.drawText(5, 11, "Objective:", "#FFFFFF");
        gui.drawText(5, 12, "Find the goal portal for each level, avoiding the", "#FFFFFF");
        gui.drawText(5, 13, "monsters and collecting the coins along the way.", "#FFFFFF");
        gui.drawText(5, 15, "Score:", "#FFFFFF");
        gui.drawText(5, 16, "The more coins you collect throughout the levels, the", "#FFFFFF");
        gui.drawText(5, 17, "more points you get.", "#FFFFFF");
        gui.drawText(5, 19, "How the game ends:", "#FFFFFF");
        gui.drawText(5, 20, "Whenever the player's health equals zero or when the", "#FFFFFF");
        gui.drawText(5, 21, "player completes all the levels.", "#FFFFFF");
        for (int i = 0; i < instructions.getOptions().size(); i++) {
            gui.drawText(5, 26 + i, instructions.getOptions().get(i), "#FFFF00");
        }
    }
}
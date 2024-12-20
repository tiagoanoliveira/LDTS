package com.t04g05.viewer.menu;
import com.t04g05.gui.GUI;

public class InstructionsViewer {
    public void draw(GUI gui) {
        gui.drawText(5, 5, "INSTRUCTIONS", "#FFFFFF");
        gui.drawText(5, 8, "Controls:", "#FFFFFF");
        gui.drawText(5, 9, "UP, DOWN, LEFT, RIGHT", "#FFFFFF");
        gui.drawText(5, 11, "Objective:", "#FFFFFF");
        gui.drawText(5, 12, "Find the goal portal for each level, avoiding the monsters and", "#FFFFFF");
        gui.drawText(5, 13, "collecting the coins along the way.", "#FFFFFF");
        gui.drawText(5, 15, "Score:", "#FFFFFF");
        gui.drawText(5, 16, "The more coins you collect throughout the levels, the more points you get.", "#FFFFFF");
        gui.drawText(5, 18, "How the game ends:", "#FFFFFF");
        gui.drawText(5, 19, "Whenever the player's health equals zero or when the player completes", "#FFFFFF");
        gui.drawText(5, 20, "all the levels.", "#FFFFFF");
        gui.drawText(5, 25, "DISCLAIMER: TO GO BACK TO THE MAIN MENU PRESS 'B'", "#D3D3D3");
    }
}
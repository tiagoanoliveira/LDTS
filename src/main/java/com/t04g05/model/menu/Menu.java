package com.t04g05.model.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public enum Mode { MAIN_MENU, INSTRUCTIONS }

    private final List<String> menuOptions;
    private final List<String> instructionsOptions;
    private int currentOption;
    private Mode mode;

    public Menu() {
        this.menuOptions = new ArrayList<>();
        this.instructionsOptions = new ArrayList<>();
        this.currentOption = 0;
        this.mode = Mode.MAIN_MENU;

        // Opções do menu
        menuOptions.add("Start Game");
        menuOptions.add("Instructions");
        menuOptions.add("Exit");

        // Texto das instruções
        instructionsOptions.add("Back");    }

    public List<String> getCurrentOptions() {
        return mode == Mode.MAIN_MENU ? menuOptions : instructionsOptions;
    }

    public int getCurrentOption() {
        return currentOption;
    }

    public void nextOption() {
        currentOption = (currentOption + 1) % getCurrentOptions().size();
    }

    public void previousOption() {
        currentOption = (currentOption - 1 + getCurrentOptions().size()) % getCurrentOptions().size();
    }

    public String getSelectedOption() {
        return getCurrentOptions().get(currentOption);
    }

    public boolean isStartGameSelected() {
        return mode == Mode.MAIN_MENU && "Start Game".equals(getSelectedOption());
    }

    public boolean isInstructionsSelected() {
        return mode == Mode.MAIN_MENU && "Instructions".equals(getSelectedOption());
    }

    public boolean isExitSelected() {
        return mode == Mode.MAIN_MENU && "Exit".equals(getSelectedOption());
    }

    public boolean isBackSelected() {
        return mode == Mode.INSTRUCTIONS && "Back".equals(getSelectedOption());
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        currentOption = 0;
    }

    public Mode getMode() {
        return mode;
    }
}


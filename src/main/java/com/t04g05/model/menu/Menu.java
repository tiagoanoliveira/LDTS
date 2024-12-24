package com.t04g05.model.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<String> options;
    private int currentOption;

    public Menu() {
        this.options = new ArrayList<>();
        this.currentOption = 0;

        // Adicionar as opções do menu
        options.add("Start Game");
        options.add("Instructions");
        options.add("Exit");
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCurrentOption() {
        return currentOption;
    }

    public void nextOption() {
        currentOption = (currentOption + 1) % options.size();
    }

    public void previousOption() {
        currentOption = (currentOption - 1 + options.size()) % options.size();
    }

    public String getSelectedOption() {
        return options.get(currentOption);
    }

    public boolean isStartGameSelected() {
        return "Start Game".equals(getSelectedOption());
    }

    public boolean isInstructionsSelected() {
        return "Instructions".equals(getSelectedOption());
    }

    public boolean isExitSelected() {
        return "Exit".equals(getSelectedOption());
    }
}

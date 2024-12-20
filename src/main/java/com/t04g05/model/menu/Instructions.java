package com.t04g05.model.menu;

import java.util.ArrayList;
import java.util.List;

public class Instructions {
    private final List<String> options;
    private int currentOption;

    public Instructions() {
        this.options = new ArrayList<>();
        this.currentOption = 0;

        options.add("Back");
    }
    public List<String> getOptions() {
        return options;
    }

    public int getCurrentOption() {
        return currentOption;
    }
    public String getSelectedOption() { return options.get(currentOption);}
    public boolean isBackSelected() {
        return "Back".equals(getSelectedOption());
    }
}

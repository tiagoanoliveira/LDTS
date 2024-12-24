package com.t04g05.model.menu;

public record Win(int score) {

    public Menu getMenu() {
        return new Menu();  // Retorna um menu básico (modificar se necessário)
    }
}

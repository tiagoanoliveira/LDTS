package com.t04g05;

import com.t04g05.arena.Arena1;
import com.t04g05.arena.Arena2;
import com.t04g05.screen.ScreenManager;

public class Main {
    public static void main(String[] args) throws InterruptedException {  // Declarar que o método pode lançar a exceção
        System.out.println("Iniciando o Nível 1...");
        Arena1 arena1 = new Arena1();

        boolean level1Completed = arena1.run();

        if (level1Completed) {

            // Atraso para garantir a limpeza da tela antes de iniciar o nível 2
            Thread.sleep(500);  // Atraso de meio segundo

            // Reiniciar o ScreenManager antes de iniciar o próximo nível
            ScreenManager.getInstance(true);  // Reiniciar a tela para o nível 2

            Arena2 arena2 = new Arena2();
            arena2.run();
        } else {
            System.out.println("O jogo terminou.");
        }
    }
}


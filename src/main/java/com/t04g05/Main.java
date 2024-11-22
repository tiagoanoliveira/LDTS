package com.t04g05;

public class Main {
    public static void main(String[] args) {
        // Criar instância de Arena1 e Arena2
        Arena1 arena1 = new Arena1();

        // Começar o jogo e garantir que o terminal da Arena1 é fechado antes de continuar
        boolean level1Completed = arena1.run();  // Executa o nível 1

        // Verificar se o nível 1 foi concluído antes de passar para a arena 2
        if (level1Completed) {
            System.out.println("Avançando para o nível 2...");
            Arena2 arena2 = new Arena2();
            arena2.run();  // Executa o nível 2
        } else {
            System.out.println("O jogo terminou.");
        }
    }
}

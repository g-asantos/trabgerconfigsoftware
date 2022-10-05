package seletor;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dominio.Exame;

public class SeletorExame {
    Scanner num;

    public SeletorExame() {
        this.num = new Scanner(System.in);
    }

    public Exame selecionar(List<Exame> lista) {
        Exame selecionado = null;
        while (selecionado == null) {
            int idSelecionado = this.mostrarOpcoes(lista);
            selecionado = lista.stream().filter((exame) -> exame.getCodigo() == idSelecionado)
                    .findFirst().orElse(null);
            if (selecionado == null) System.out.println("Código inválido, tente novamnente");
        }

        return selecionado;
    }

    private int mostrarOpcoes(List<Exame> lista) {
        while (true) {
            try {
                System.out.println("Selecione o exame informando o código: ");
                lista.forEach((exame) -> System.out.println(exame.getCodigo() +
                        " - " + exame.getNome()));
                return this.num.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Ops...parece que voce digitou um valor invalido.");
                num.nextLine();
            }
        }
    }

}

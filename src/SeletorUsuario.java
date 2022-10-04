import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import dominio.Usuario;

public class SeletorUsuario {
    Scanner num;

    SeletorUsuario() {
        this.num = new Scanner(System.in);
    }

    public Usuario selecionar(List<Usuario> lista) {
        Usuario selecionado = null;
        while(selecionado == null) {
            int idSelecionado = this.mostrarOpcoes(lista);
            selecionado = lista.stream().filter((usuario) -> usuario.getId() == idSelecionado)
                               .findFirst().orElse(null);
        }

        return selecionado;
    }

    private int mostrarOpcoes(List<Usuario> lista) {
        while(true){
            try{
                System.out.println("Selecione o usuário informando o código: ");
                lista.forEach((usuario) -> System.out.println(usuario.getId() + 
                                         " - " + usuario.getNome() + " - " + 
                                         usuario.getTipo().getNome()));
                return this.num.nextInt();

            }catch(InputMismatchException ex){
                System.out.println("Ops...parece que voce digitou um valor invalido.");
                num.nextLine();
            }
        }
    }

}

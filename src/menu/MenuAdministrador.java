package menu;
import dominio.TipoUsuario;
import dominio.Usuario;
import repository.UsuarioRepository;
import sessao.Sessao;

import java.util.List;
import java.util.Scanner;

public class MenuAdministrador extends Menu {
    
    
    public MenuAdministrador() {
        this.menuAdministrador = this;
        this.teclado = new Scanner(System.in);
        this.sessao = Sessao.getInstance();
        // this.autorizacaoExame = UsuarioRepository.getInstance();
        // this.seletorUsuario = new SeletorUsuario();
    }

    //Inicio opções
    @Override
    protected void showSubMenu() {
        System.out.println("1 - Incluir novo usuário");
        System.out.println("2 - Ver autorizações por nome de usuário");
        String respostaUsuario = this.teclado.next();
        if (respostaUsuario.equals("2")) {
            this.escolhaMenu(respostaUsuario);
            this.incluirNovoUsuario();
        }
    }

    @Override
    void escolhaMenu(String input) {
        System.out.println("Menu ADMIN - Usuário escolheu opção " + input);
    }

    public void incluirNovoUsuario() {
        
        TipoUsuario aux;
        System.out.println("Digite o nome do novo usuário: ");
        String respostaUm = teclado.nextLine();
        System.out.println("Digite qual o tipo de usuário: ");
        String respostaDois = teclado.nextLine();
        if (respostaDois.equals(TipoUsuario.ADMIN.getNome())){
            respostaDois = TipoUsuario.ADMIN.getNome();
        }else if (respostaDois.equals(TipoUsuario.MEDICO.getNome())){
            respostaDois = TipoUsuario.ADMIN.getNome();
        }else if (respostaDois.equals(TipoUsuario.PACIENTE.getNome())){
            respostaDois = TipoUsuario.PACIENTE.getNome();
        }
        Usuario usuarios = usuarioRepository.criar(respostaUm, respostaDois);
    }
}

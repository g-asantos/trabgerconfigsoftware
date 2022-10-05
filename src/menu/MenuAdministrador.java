package menu;
import dominio.TipoUsuario;
import dominio.Usuario;
import dominio.AutorizacaoExame;
import repository.UsuarioRepository;
import repository.AutorizacaoRepository;
import sessao.Sessao;

import java.util.List;
import java.util.Scanner;

public class MenuAdministrador extends Menu {
    
    protected AutorizacaoRepository usuarioRepository;
    
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

        System.out.println("Ver autorizações por nome de usuário");
        ArrayList<autorizacaoExame> adm =  usuarioRepository.listar();
        Usuario selecionado = seletorUsuario.selecionar(adm);
        sessao.setUsuarioLogado(selecionado);
        if (selecionado.getTipo() == TipoUsuario.ADMIN) menuAdministrador.show();
        else if (selecionado.getTipo() == TipoUsuario.MEDICO) menuMedico.show();
        else if (selecionado.getTipo() == TipoUsuario.PACIENTE) menuPaciente.show();
    }
}

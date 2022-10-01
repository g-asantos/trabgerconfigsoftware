package menu;

import dominio.TipoUsuario;
import dominio.Usuario;

import java.util.Scanner;

public abstract class Menu {
    // SeletorUsuario seletorUsuario
    // Sessao sessao
    // UsuarioRepository usuarioRepository
    protected Menu menuMedico;
    protected Menu menuPaciente;
    protected Menu menuAdministrador;
    protected Scanner teclado;

    public Menu() {
        this.teclado = new Scanner(System.in);
        // this.sessao = Sessao.getInstance();
        // this.usuarioRepository = UsuarioRepository.getInstance();
    }

    public void setMenuMedico(Menu menuMedico) {
        this.menuMedico = menuMedico;
    }

    public void setMenuPaciente(Menu menuPaciente) {
        this.menuPaciente = menuPaciente;
    }

    public void setMenuAdministrador(Menu menuAdministrador) {
        this.menuAdministrador = menuAdministrador;
    }

    public void show() {
        // Usuario usuarioLogado = this.sessao.getUsuarioLogado();
        Usuario usuarioLogado = new Usuario(1, "Alexia Dorneles", TipoUsuario.PACIENTE);
        System.out.println();
        System.out.println(">> logado como " + usuarioLogado.getIniciais() + " - " + usuarioLogado.getTipo().getNome());
        System.out.println("---");
        System.out.println("0 - trocar usuário");
        this.showSubMenu();
    }

    abstract void showSubMenu();

    public void trocarUsuario() {
        // List<Usuario> usuarios = usuarioRepository.listar();
        // Usuario selecionado = seletorUsuario.selecionar(usuarios);
        Usuario selecionado = new Usuario(1, "Alexia", TipoUsuario.PACIENTE);
        // sessao.setUsuarioLogado(usuario)
        if (selecionado.getTipo() == TipoUsuario.ADMIN) menuAdministrador.show();
        else if (selecionado.getTipo() == TipoUsuario.MEDICO) menuMedico.show();
        else if (selecionado.getTipo() == TipoUsuario.PACIENTE) menuPaciente.show();
    }

}

package menu;

import dominio.TipoUsuario;
import dominio.Usuario;
import repository.UsuarioRepository;
import seletor.SeletorUsuario;
import sessao.Sessao;

import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected SeletorUsuario seletorUsuario;
    protected Sessao sessao;
    protected UsuarioRepository usuarioRepository;
    protected Menu menuMedico;
    protected Menu menuPaciente;
    protected Menu menuAdministrador;
    protected Scanner teclado;

    public Menu() {
        this.teclado = new Scanner(System.in);
        this.sessao = Sessao.getInstance();
        this.usuarioRepository = UsuarioRepository.getInstance();
        this.seletorUsuario = new SeletorUsuario();
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
        Usuario usuarioLogado = this.sessao.getUsuarioLogado();
        System.out.println();
        System.out.println(">> logado como " + usuarioLogado.getIniciais() + " - " + usuarioLogado.getTipo().getNome());
        System.out.println("---");
        System.out.println("0 - trocar usuário");
        this.showSubMenu();
        System.out.println("99 - encerrar programa");
        String respostaUsuario = this.teclado.next();
        if (respostaUsuario.equals("0")) {
            this.trocarUsuario();
            return;
        }

        if (respostaUsuario.equals("99")) return;
        else this.escolhaMenu(respostaUsuario);
        this.show();
    }

    protected abstract void showSubMenu();

    abstract void escolhaMenu(String input);

    public void trocarUsuario() {
        List<Usuario> usuarios = usuarioRepository.listar();
        Usuario selecionado = seletorUsuario.selecionar(usuarios);
        sessao.setUsuarioLogado(selecionado);
        if (selecionado.getTipo() == TipoUsuario.ADMIN) menuAdministrador.show();
        else if (selecionado.getTipo() == TipoUsuario.MEDICO) menuMedico.show();
        else if (selecionado.getTipo() == TipoUsuario.PACIENTE) menuPaciente.show();
    }

}

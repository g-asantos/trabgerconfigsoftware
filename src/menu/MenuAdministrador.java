package menu;

import dominio.AutorizacaoExame;
import dominio.TipoUsuario;
import dominio.Usuario;
import repository.AutorizacaoRepository;

import java.util.List;

public class MenuAdministrador extends Menu {
    private AutorizacaoRepository autorizacaoRepository;

    public MenuAdministrador() {
        this.menuAdministrador = this;
        this.autorizacaoRepository = AutorizacaoRepository.getInstance();
    }

    @Override
    protected void showSubMenu() {
        System.out.println("1 - Incluir novo usuário");
        System.out.println("2 - Ver autorizações por nome de usuário");
        System.out.println("3 - Ver estatística");
    }

    @Override
    void escolhaMenu(String input) {
        switch (input) {
            case "1":
                this.incluirNovoUsuario();
                break;
            case "2":
                this.verAutorizações();
                break;
            case "3":
                break;
            default:
                System.out.println("Opção inválida, tente novamente");
        }
    }

    private void incluirNovoUsuario() {
        TipoUsuario tipo = this.getTipoUsuario();
        System.out.println("Escreva o nome do Usuário que você deseja criar: ");
        this.teclado.nextLine();
        String nome = this.teclado.nextLine();
        this.usuarioRepository.criar(nome, tipo);
        System.out.println("Usuário criado com sucesso!");
    }

    private TipoUsuario getTipoUsuario() {
        TipoUsuario tipoUsuario = null;
        while (tipoUsuario == null) {
            System.out.println("Selecione o tipo de Usuário que você deseja criar: ");
            System.out.println("1 - Administrador ");
            System.out.println("2 - Médico");
            System.out.println("3 - Paciente");
            String selecionado = this.teclado.next();
            if (selecionado.equals("1")) tipoUsuario = TipoUsuario.ADMIN;
            if (selecionado.equals("2")) tipoUsuario = TipoUsuario.MEDICO;
            if (selecionado.equals("3")) tipoUsuario = TipoUsuario.PACIENTE;
            if (tipoUsuario == null) System.out.println("Opção inválida, tente novamente");
        }

        return tipoUsuario;
    }


    private void verAutorizações() {
        Usuario usuario = this.seletorUsuario.selecionar(this.usuarioRepository.listar());
        List<AutorizacaoExame> listAutorizacao = this.autorizacaoRepository.listarPorPaciente(usuario);
        System.out.println(listAutorizacao);

    }
}

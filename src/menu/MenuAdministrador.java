package menu;

import dominio.TipoUsuario;
import dominio.Usuario;
import repository.AutorizacaoRepository;
import repository.UsuarioRepository;
import seletor.SeletorUsuario;
import seletor.SeletorExame;

import java.util.*;

public class MenuAdministrador extends Menu {

    private AutorizacaoRepository autorizacaoRepository = AutorizacaoRepository.getInstance();
    private UsuarioRepository usuarioRepository;
    private TipoUsuario tipoUsuario;
    private SeletorUsuario seletorUsuario;
    private SeletorExame seletorExame;

    public MenuAdministrador() {
        this.seletorUsuario = new SeletorUsuario();
        this.seletorExame = new SeletorExame();
        this.usuarioRepository = new UsuarioRepository();
        this.menuAdministrador = this;

    }

    // Inicio opções
    @Override
    protected void showSubMenu() {
        while (true) {
            try {
                System.out.println("1 - Incluir novo usuário");
                System.out.println("2 - Ver autorizações por nome de usuário");
                int num = this.teclado.nextInt();
                this.escolhaMenu(Integer.toString(num));
                if (num == 1) {
                    this.incluirNovoUsuario();
                } else {
                    System.out.println("Opção inexistente. Tente novamente");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Ops...parece que voce digitou um valor invalido.");
                this.teclado.nextLine();
            }
        }
    }

    @Override
    void escolhaMenu(String input) {
        System.out.println("Menu ADMIN - Usuário escolheu opção " + input);
    }

    public void incluirNovoUsuario() {
        boolean estado = true;
        while(estado == true){
            try{
            System.out.println("Selecione o tipo de Usuário que você deseja criar: ");
            System.out.println("1 - Administrador ");
            System.out.println("2 - Médico");
            System.out.println("3 - Paciente");
            Scanner scanner = new Scanner(System.in);
            int tipoUsuNovo = scanner.nextInt();
            if(tipoUsuNovo == 1){
                TipoUsuario tipoUsu = TipoUsuario.ADMIN;
                System.out.println("Escreva o nome do Usuário que você deseja criar: ");
                scanner.nextLine();
                String nomeUsuNovo = scanner.nextLine();
                this.usuarioRepository.criar(nomeUsuNovo, tipoUsu);
                estado = false;
            } else if(tipoUsuNovo == 2){
                TipoUsuario tipoUsu = TipoUsuario.MEDICO;
                System.out.println("Escreva o nome do Usuário que você deseja criar: ");
                scanner.nextLine();
                String nomeUsuNovo = scanner.nextLine();
                this.usuarioRepository.criar(nomeUsuNovo, tipoUsu);
                estado = false;
            } else if(tipoUsuNovo == 3){
                TipoUsuario tipoUsu = TipoUsuario.PACIENTE;
                System.out.println("Escreva o nome do Usuário que você deseja criar: ");
                scanner.nextLine();
                String nomeUsuNovo = teclado.nextLine();
                this.usuarioRepository.criar(nomeUsuNovo, tipoUsu);
                estado = false;
            }
        }catch (InputMismatchException ex) {
            System.out.println("Ops...parece que voce digitou um valor invalido.");
            this.teclado.nextLine();
        }       
        }
    }
}

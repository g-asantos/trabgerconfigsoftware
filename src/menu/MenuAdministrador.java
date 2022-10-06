package menu;

import dominio.TipoUsuario;
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
        this.tipoUsuario = new TipoUsuario();
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
            int tipoUsuNovo = teclado.nextInt();
            if(tipoUsuNovo == 1){
                String tipoUsuario = new TipoUsuario();
                System.out.println(tipoUsuario);
                System.out.println("Escreva o nome do Usuário que você deseja criar: ");
                String nomeUsuNovo = teclado.nextLine();
                UsuarioRepository usuarioRepository = new UsuarioRepository();
                usuarioRepository.criar(nomeUsuNovo, );
                System.out.println("");
                estado = false;
            } else if(tipoUsuNovo == 2){
                String tipoUsu = TipoUsuario.MEDICO.getNome();
                System.out.println(tipoUsu);
                this.show();
            } else if(tipoUsuNovo == 3){
                String tipoUsu = TipoUsuario.PACIENTE.getNome();
                System.out.println(tipoUsu);
                this.show();
            }
        }catch (InputMismatchException ex) {
            System.out.println("Ops...parece que voce digitou um valor invalido.");
            this.teclado.nextLine();
        }       
        }
    }
}

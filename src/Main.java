import cargaDados.CargaDados;
import menu.Menu;
import menu.MenuAdministrador;
import menu.MenuMedico;
import menu.MenuPaciente;
import repository.ExameRepository;
import repository.UsuarioRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        CargaDados cargaDados = new CargaDados(UsuarioRepository.getInstance(), ExameRepository.getInstance());
        cargaDados.carregar();

        Menu menuMedico = new MenuMedico();
        Menu menuPaciente = new MenuPaciente();
        Menu menuAdministrador = new MenuAdministrador();

        menuMedico.setMenuAdministrador(menuAdministrador);
        menuMedico.setMenuPaciente(menuPaciente);
        menuPaciente.setMenuAdministrador(menuAdministrador);
        menuPaciente.setMenuMedico(menuMedico);
        menuAdministrador.setMenuPaciente(menuPaciente);
        menuAdministrador.setMenuMedico(menuMedico);

        System.out.println("Escolha o usuário que deseja logar: ");
        menuMedico.trocarUsuario();
    }
}
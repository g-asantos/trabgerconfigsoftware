package menu;

public class MenuPaciente extends Menu {
    public MenuPaciente() {
        this.menuPaciente = this;
    }

    @Override
    void showSubMenu() {
        System.out.println("------ PACIENTE ------");
        String input = this.teclado.next();

        switch (input) {
            case "0":
                this.trocarUsuario();
                break;
            default:
                this.show();
        }

    }
}

package menu;

public class MenuMedico extends Menu {
    public MenuMedico() {
        this.menuMedico = this;
    }

    @Override
    void showSubMenu() {
        System.out.println("------ MEDICO ------");
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

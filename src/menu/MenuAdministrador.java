package menu;

public class MenuAdministrador extends Menu {

    public MenuAdministrador() {
        this.menuAdministrador = this;
    }

    @Override
    void showSubMenu() {
        System.out.println("------ ADMINISTRADOR ------");
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

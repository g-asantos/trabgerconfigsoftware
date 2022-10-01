package menu;

public class MenuAdministrador extends Menu {

    public MenuAdministrador() {
        this.menuAdministrador = this;
    }

    @Override
    protected void showSubMenu() {
        System.out.println("1 - opção qualquer do administrador");
    }

    }
}

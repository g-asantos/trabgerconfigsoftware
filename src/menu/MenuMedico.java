package menu;

public class MenuMedico extends Menu {
    public MenuMedico() {
        this.menuMedico = this;
    }

    @Override
    protected void showSubMenu() {
        System.out.println("1 - opção qualquer do médico");
    }

    }
}

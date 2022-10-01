package menu;

public class MenuPaciente extends Menu {
    public MenuPaciente() {
        this.menuPaciente = this;
    }

    @Override
    protected void showSubMenu() {
        System.out.println("1 - opção qualquer do paciente");
    }

    }
}

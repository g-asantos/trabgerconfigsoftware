package menu;

public class MenuPaciente extends Menu {
    public MenuPaciente() {
        this.menuPaciente = this;
    }

    @Override
    protected void showSubMenu() {
        System.out.println("1 - opção qualquer do paciente");
    }

    @Override
    void escolhaMenu(String input) {
        System.out.println("Menu PACIENTE - Usuário escolheu opção " + input);
    }
}

package menu;

public class MenuMedico extends Menu {
    public MenuMedico() {
        this.menuMedico = this;
    }

    @Override
    protected void showSubMenu() {
        System.out.println("1 - opção qualquer do médico");
    }

    @Override
    void escolhaMenu(String input) {
        System.out.println("Menu MEDICO - Usuário escolheu opção " + input);
    }
}

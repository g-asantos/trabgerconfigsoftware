package menu;

public class MenuAdministrador extends Menu {

    public MenuAdministrador() {
        this.menuAdministrador = this;
    }

    //Inicio opções
    @Override
    protected void showSubMenu() {
        System.out.println("1 - opção qualquer do administrador");
    }

    @Override
    void escolhaMenu(String input) {
        System.out.println("Menu ADMIN - Usuário escolheu opção " + input);
    }
}

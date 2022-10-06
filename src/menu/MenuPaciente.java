package menu;

import dominio.AutorizacaoExame;
import repository.AutorizacaoRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MenuPaciente extends Menu {

    AutorizacaoRepository autorizacaoRepository;

    public MenuPaciente() {
        this.menuPaciente = this;
        this.autorizacaoRepository = AutorizacaoRepository.getInstance();
    }

    @Override
    protected void showSubMenu() {
        System.out.println("1 - Marcar exame como realizado");
        System.out.println("2 - Minhas autorizações");
    }

    @Override
    void escolhaMenu(String input) {
        switch (input) {
            case "1":
                this.realizarExame();
                break;
            case "2":
                this.meusExames();
                break;
        }
    }

    private void realizarExame() {

    }

    private void meusExames() {
        ArrayList<AutorizacaoExame> meusExames = this.autorizacaoRepository.listarPorPaciente(this.sessao.getUsuarioLogado());
        List<AutorizacaoExame> meusExamesOrdenados = meusExames.stream().sorted(Comparator.comparing(AutorizacaoExame::getDataCadastro)).collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        meusExamesOrdenados.forEach(exame -> {
            String dataCadastroFormatada = exame.getDataCadastro().format(formatter);
            String realizado = "(não realizado)";
            if (exame.getDataRealizacaoExame() != null) {
                realizado = exame.getDataRealizacaoExame().format(formatter);
            }
            String exameMaisMedico = exame.getCodigo() + " - " + exame.getExame().getNome() + " solicitado por " + exame.getMedicoSolicitante().getNome();
            System.out.println(exameMaisMedico + " em " + dataCadastroFormatada + " " + realizado);
        });
    }
}

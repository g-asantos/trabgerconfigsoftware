package menu;

import dominio.AutorizacaoExame;
import repository.AutorizacaoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
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
        List<AutorizacaoExame> meusExames = this.autorizacaoRepository.listarPorPaciente(this.sessao.getUsuarioLogado())
                .stream().filter(exame -> exame.getDataRealizacaoExame() == null).collect(Collectors.toList());

        if (meusExames.isEmpty()) {
            System.out.println("Nenhum exame pendente");
            return;
        }

        AutorizacaoExame selecionado = this.selecionarAutorizacao(meusExames);
        System.out.println("Informe a data de realização (no formato dd/mm/yyyy): ");
        LocalDate dataRealizacao = this.getDataRealizacao();
        boolean dataEhValida = this.validarData(dataRealizacao, selecionado);
        if (dataEhValida) {
            selecionado.setDataRealizacaoExame(dataRealizacao);
        } else {
            System.out.println(
                    "Data inválida: a data informada é anterior à da solicitação ou posterior a 30 dias da solicitação ");
        }
    }

    private boolean validarData(LocalDate dataRealizacao, AutorizacaoExame autorizacao) {
        if (dataRealizacao.isBefore(autorizacao.getDataCadastro())) {
            return false;
        }

        return !autorizacao.getDataCadastro().plusDays(30).isBefore(dataRealizacao);
    }

    private AutorizacaoExame selecionarAutorizacao(List<AutorizacaoExame> meusExames) {
        System.out.println("Selecione o exame pelo código: ");
        AutorizacaoExame selecionado = null;

        while (Objects.isNull(selecionado)) {
            meusExames.stream().map(AutorizacaoExame::toString).forEach(System.out::println);
            int codigo = this.teclado.nextInt();
            selecionado = meusExames.stream().filter(exame -> exame.getCodigo() == codigo).findFirst().orElse(null);
            if (selecionado == null) {
                System.out.println("Código inválido, tente novamente");
            }
        }

        return selecionado;
    }

    private LocalDate getDataRealizacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            try {
                String data = this.teclado.next();
                return LocalDate.parse(data, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida, tente novamente");
            }
        } while (true);
    }

    private void meusExames() {
        ArrayList<AutorizacaoExame> meusExames = this.autorizacaoRepository
                .listarPorPaciente(this.sessao.getUsuarioLogado());
        List<AutorizacaoExame> meusExamesOrdenados = meusExames.stream()
                .sorted(Comparator.comparing(AutorizacaoExame::getDataCadastro)).collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        meusExamesOrdenados.forEach(exame -> {
            String realizado = "(não realizado)";
            if (exame.getDataRealizacaoExame() != null) {
                realizado = "(realizado em " + exame.getDataRealizacaoExame().format(formatter) + ")";
            }
            System.out.println(exame + " " + realizado);
        });
    }

}

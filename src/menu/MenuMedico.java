package menu;

import dominio.AutorizacaoExame;
import dominio.Exame;
import dominio.TipoUsuario;
import dominio.Usuario;
import repository.AutorizacaoRepository;
import repository.ExameRepository;
import seletor.SeletorExame;
import seletor.SeletorUsuario;

import java.util.*;

public class MenuMedico extends Menu {
    private ExameRepository exameRepository = ExameRepository.getInstance();
    private AutorizacaoRepository autorizacaoRepository = AutorizacaoRepository.getInstance();
    private SeletorExame seletorExame;
    private SeletorUsuario seletorUsuario;

    public MenuMedico() {
        this.seletorExame = new SeletorExame();
        this.seletorUsuario = new SeletorUsuario();
        this.menuMedico = this;
    }

    @Override
    protected void showSubMenu() {
        System.out.println("1 - Criar nova autorizacao de exame");
        System.out.println("2 - Listar autorizacoes");
        int num = this.teclado.nextInt();
        this.escolhaMenu(Integer.toString(num));
    }

    @Override
    void escolhaMenu(String input) {
        System.out.println("Menu MEDICO - Usuário escolheu opção " + input);
        while (true) {
            try {
                if (Integer.parseInt(input) == 1) {
                    this.criarAutorizacao();
                } else if (Integer.parseInt(input) == 2) {
                    this.listarAutorizacoes();
                } else {
                    System.out.println("Opção inexistente. Tente novamente");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Ops...parece que voce digitou um valor invalido.");
                this.teclado.nextLine();
            }
        }
    }

    private void criarAutorizacao() {
        Usuario usuario = this.seletorUsuario.selecionar(this.usuarioRepository.listar());
        Exame exame = this.seletorExame.selecionar(this.exameRepository.listar());
        Usuario medico = this.seletorUsuario.selecionar(this.usuarioRepository.listar().stream().filter(u -> u.getTipo() == TipoUsuario.MEDICO).toList());

        autorizacaoRepository.criar(usuario, exame, medico);
        System.out.println("Autorizacao criada!");
        this.showSubMenu();
    }

    private void listarAutorizacoes() {
        boolean estaRodando = true;
        while (estaRodando) {
            try {
                System.out.println("1 - Listar por paciente");
                System.out.println("2 - Listar por tipo de exame");
                System.out.println("99 - Retornar");
                int num = this.teclado.nextInt();

                if (num == 1) {
                    Usuario usuario = this.seletorUsuario.selecionar(this.usuarioRepository.listar());
                    List<AutorizacaoExame> listaPaciente = this.autorizacaoRepository.listarPorPaciente(usuario).stream().toList();
                    List<AutorizacaoExame> listaPacienteOrdenada = listaPaciente.stream().sorted(Comparator.comparing(AutorizacaoExame::getDataRealizacaoExame)).toList();
                    if (listaPacienteOrdenada.size() < 1) {
                        System.out.println("Não há autorizações registradas");
                        estaRodando = false;
                        this.show();
                    } else {
                        listaPacienteOrdenada.forEach(list ->
                                System.out.println("Código: " + list.getCodigo() + " - Exame: " +
                                        list.getExame().getNome() + " - Paciente: " +
                                        list.getPaciente().getNome() + " - Data de Realizacão: " +
                                        (list.getDataRealizacaoExame() != null ?
                                                list.getDataRealizacaoExame().toString() : "Exame ainda não realizado")));
                    }
                } else if (num == 2) {
                    Exame exame = this.seletorExame.selecionar(this.exameRepository.listar());
                    List<AutorizacaoExame> listaExame = this.autorizacaoRepository.listarPorExame(exame).stream().toList();
                    List<AutorizacaoExame> listaExameOrdenada = listaExame.stream().sorted(Comparator.comparing(AutorizacaoExame::getDataRealizacaoExame)).toList();
                    if (listaExameOrdenada.size() < 1) {
                        System.out.println("Não há autorizações registradas");
                        estaRodando = false;
                        this.show();
                    } else {
                        listaExameOrdenada.forEach(list ->
                                System.out.println("Código: " + list.getCodigo() + " - Exame: " +
                                        list.getExame().getNome() + " - Paciente: " +
                                        list.getPaciente().getNome() + " - Data de realização: "
                                        + (list.getDataRealizacaoExame() != null ?
                                        list.getDataRealizacaoExame().toString() : "Exame ainda não realizado")));
                    }
                } else if (num == 99) {
                    estaRodando = false;
                    this.show();
                } else {
                    System.out.println("Opção inexistente. Tente novamente");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Ops...parece que voce digitou um valor invalido.");
                this.teclado.nextLine();
            }
        }
    }
}

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
import java.util.stream.Collectors;

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
    }

    @Override
    void escolhaMenu(String input) {
        switch (input) {
            case "1":
                this.criarAutorizacao();
                break;
            case "2":
                this.listarAutorizacoes();
                break;
            default:
                System.out.println("Opção inexistente. Tente novamente");
        }
    }

    private void criarAutorizacao() {
        Usuario usuario = this.seletorUsuario.selecionar(this.usuarioRepository.listarPorTipo(TipoUsuario.PACIENTE));
        Exame exame = this.seletorExame.selecionar(this.exameRepository.listar());
        Usuario medico = this.seletorUsuario.selecionar(this.usuarioRepository.listarPorTipo(TipoUsuario.MEDICO));

        autorizacaoRepository.criar(usuario, exame, medico);
        System.out.println("Autorizacao criada!");
    }

    private void listarAutorizacoes() {
            try {
                System.out.println("1 - Listar por paciente");
                System.out.println("2 - Listar por tipo de exame");
                int num = this.teclado.nextInt();
                Comparator<AutorizacaoExame> comparator = Comparator.comparing(AutorizacaoExame::getDataRealizacaoExame);
                if (num == 1) {
                    Usuario usuario = this.seletorUsuario.selecionar(this.usuarioRepository.listarPorTipo(TipoUsuario.PACIENTE));
                    List<AutorizacaoExame> listaPaciente = this.autorizacaoRepository.listarPorPaciente(usuario).stream().collect(Collectors.toList());
                    listaPaciente.sort(comparator);
                    if (listaPaciente.size() < 1) {
                        System.out.println("Não há autorizações registradas");
                    } else {
                        listaPaciente.forEach(list -> System.out.println(list.toString()));
                    }
                } else if (num == 2) {
                    Exame exame = this.seletorExame.selecionar(this.exameRepository.listar());
                    List<AutorizacaoExame> listaExame = this.autorizacaoRepository.listarPorExame(exame)
                            .stream().collect(Collectors.toList());
                    listaExame.sort(comparator);
                    if (listaExame.size() < 1) {
                        System.out.println("Não há autorizações registradas");
                    } else {
                        listaExame.forEach(list -> System.out.println(list.toString()));
                    }
                } else {
                    System.out.println("Opção inexistente. Tente novamente");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Ops...parece que voce digitou um valor invalido.");
                this.teclado.nextLine();
            }
        }

}

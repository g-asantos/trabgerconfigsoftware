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
        System.out.println("2 - Listar autorizacoes por paciente");
        System.out.println("3 - Lista autorizacoes por tipo de exame");
        System.out.println("4 - Lista autorizacoes solicitadas por mim");
    }

    @Override
    void escolhaMenu(String input) {
        switch (input) {
            case "1":
                this.criarAutorizacao();
                break;
            case "2":
                this.listarAutorizacoesPorPaciente();
                break;
            case "3":
                this.listarAutorizacoesPorExame();
                break;
            case "4":
                this.minhasAutorizacoes();
                break;
            default:
                System.out.println("Opção inexistente. Tente novamente");
        }
    }

    private void criarAutorizacao() {
        Usuario usuario = this.seletorUsuario.selecionar(this.usuarioRepository.listarPorTipo(TipoUsuario.PACIENTE));
        Exame exame = this.seletorExame.selecionar(this.exameRepository.listar());

        autorizacaoRepository.criar(usuario, exame, this.sessao.getUsuarioLogado());
        System.out.println("Autorizacao criada!");
    }

    private void listarAutorizacoesPorPaciente() {
        Usuario usuario = this.seletorUsuario
                .selecionar(this.usuarioRepository.listarPorTipo(TipoUsuario.PACIENTE));
        List<AutorizacaoExame> listaPaciente = this.autorizacaoRepository.listarPorPaciente(usuario);
        this.printSortedList(listaPaciente);
    }

    private void listarAutorizacoesPorExame() {
        Exame exame = this.seletorExame.selecionar(this.exameRepository.listar());
        List<AutorizacaoExame> listaExame = this.autorizacaoRepository.listarPorExame(exame);
        this.printSortedList(listaExame);
    }

    private void minhasAutorizacoes() {
        ArrayList<AutorizacaoExame> minhas = this.autorizacaoRepository.listarPorMedico(this.sessao.getUsuarioLogado());
        this.printSortedList(minhas);
    }

    private void printSortedList(List<AutorizacaoExame> list) {
        Comparator<AutorizacaoExame> comparator = Comparator.comparing(AutorizacaoExame::getDataCadastro);
        list.sort(comparator);
        if (list.size() < 1) {
            System.out.println("Não há autorizações registradas");
        } else {
            list.forEach(l -> System.out.println(l.toString()));
        }
    }
}

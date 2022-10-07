package estatistica;

import repository.UsuarioRepository;
import repository.AutorizacaoRepository;
import dominio.TipoUsuario;

public class Estatistica {
    private UsuarioRepository usuarioRepository;
    private AutorizacaoRepository autorizacaoRepository;

    public Estatistica() {
        this.usuarioRepository = UsuarioRepository.getInstance();
        this.autorizacaoRepository = AutorizacaoRepository.getInstance();
    }
    
    /* Retorna o numero de medicos */
    public int numeroMedicos() {
        return usuarioRepository.listarPorTipo(TipoUsuario.MEDICO).size();
    }

    /* Retorna o numero de pacientes */
    public int numeroPacientes() {
        return usuarioRepository.listarPorTipo(TipoUsuario.PACIENTE).size();
    }

    /* Retorna o numero de autorizacoes */
    public int numeroAutorizacoes() {
        return autorizacaoRepository.listar().size();
    }

    /* Retorna o percentual de realizados */
    public int percentualRealizados() {
        double totalAutorizados = autorizacaoRepository.listar().size();
        double totalRealizados = autorizacaoRepository.listarRealizados().size();
        if (totalRealizados == 0) return 0;
        return (int) ((totalRealizados / totalAutorizados) * 100);
    }
}

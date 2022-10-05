package estatistica;

import repository.UsuarioRepository;
import repository.AutorizacaoRepository;
import dominio.TipoUsuario;

public class Estatistica {
    private UsuarioRepository usuarioRepository;
    private AutorizacaoRepository autorizacaoRepository;
    
    /* Retorna o numero de medicos */
    public int numeroMedicos() {
        return usuarioRepository.listarPorTipo(TipoUsuario.MEDICO).size();
    }

    /* Retorna o numero de pacientes */
    public int numeroPacientes() {
        return usuarioRepository.listarPorTipo(TipoUsuario.PACIENTE).size();
    }
}
/*teste*/

package repository;

import dominio.Usuario;
import dominio.TipoUsuario;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRepository {
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public Usuario Criar(int id, String nome, TipoUsuario tipoUsuario) {
        Usuario usuario = new Usuario(id, nome, tipoUsuario);
        listaUsuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listar() {

        return listaUsuarios;
    }

    public List<Usuario> listarPorTipo(TipoUsuario tipoUsuario) {
        List<Usuario> listaTipo = new ArrayList<>();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getTipo() == tipoUsuario) {
                listaTipo.add(usuario);
            }
        }
        return listaTipo;
    }

    public List<Usuario> listarMedicoOuPacientePorNome(String nome) {
        List<Usuario> listaNome = new ArrayList<>();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNome() == nome && usuario.getTipo() == TipoUsuario.PACIENTE || usuario.getTipo() == TipoUsuario.MEDICO) {
                listaNome.add(usuario);
            }
        }
        return listaNome;
    }
}
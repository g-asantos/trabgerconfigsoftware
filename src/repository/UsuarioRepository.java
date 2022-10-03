/*testes*/

package repository;

import dominio.Usuario;
import dominio.TipoUsuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private static UsuarioRepository instance;
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static int id;
    public static UsuarioRepository getInstance(){
        if(instance == null){
            instance = new UsuarioRepository();
        }
        return instance;
    }

    public Usuario Criar(String nome, TipoUsuario tipoUsuario) {
        Usuario usuario = new Usuario(proxId(), nome, tipoUsuario);
        listaUsuarios.add(usuario);
        return usuario;
    }

    public ArrayList<Usuario> listar() {

        return listaUsuarios;
    }

    public ArrayList<Usuario > listarPorTipo(TipoUsuario tipoUsuario) {
        ArrayList<Usuario > listaTipo = new ArrayList<>();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getTipo() == tipoUsuario) {
                listaTipo.add(usuario);
            }
        }
        return listaTipo;
    }

    public ArrayList<Usuario > listarMedicoOuPacientePorNome(String nome) {
        ArrayList<Usuario > listaNome = new ArrayList<>();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNome() == nome && usuario.getTipo() == TipoUsuario.PACIENTE || usuario.getTipo() == TipoUsuario.MEDICO) {
                listaNome.add(usuario);
            }
        }
        return listaNome;
    }
    public int proxId(){
        try {
            int id = listaUsuarios.get(listaUsuarios.size() - 1).getId();
        }
        catch (IndexOutOfBoundsException e){
            int id = 1;
        }
        return id;
    }
}
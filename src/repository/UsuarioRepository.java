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

    private UsuarioRepository() {
    }

    public Usuario criar(String nome, TipoUsuario tipoUsuario) {
        Usuario usuario = new Usuario(UsuarioRepository.id++, nome, tipoUsuario);
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
            if (usuario.getNome().contains(nome) && usuario.getTipo() == TipoUsuario.PACIENTE || usuario.getTipo() == TipoUsuario.MEDICO) {
                listaNome.add(usuario);
            }
        }
        return listaNome;
    }
}
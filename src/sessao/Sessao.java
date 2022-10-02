package sessao;

import dominio.Usuario;

public class Sessao {
    private static Sessao instance;
    private Usuario usuarioLogado;
    
    public static Sessao getInstance() {
        if (instance == null) {
            instance = new Sessao();
        }

        return instance;
    }

    private Sessao() {}

    public Usuario getUsuarioLogado(){
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado){
        this.usuarioLogado = usuarioLogado;
    }
}

package dominio;

public class Usuario {
    int id;
    String nome;
    TipoUsuario tipo;

    public Usuario(int id, String nome, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    public TipoUsuario getTipo() {
        return tipo;
    }
}

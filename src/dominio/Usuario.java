package dominio;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    public String getIniciais() {
        return Arrays.stream(this.nome.split(" "))
                .map(str -> String.valueOf(str.charAt(0)))
                .collect(Collectors.joining());
    }
}

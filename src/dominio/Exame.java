package dominio;

public class Exame {
    int codigo;
    String nome;

    public Exame(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}

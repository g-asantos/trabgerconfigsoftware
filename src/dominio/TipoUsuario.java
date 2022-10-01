package dominio;

public enum TipoUsuario {
    ADMIN("Administrador"),
    MEDICO("Médico"),
    PACIENTE("Paciente");

    String nome;

    TipoUsuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

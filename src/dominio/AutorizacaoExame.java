package dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AutorizacaoExame {
    int codigo;
    LocalDateTime dataCadastro;
    Usuario medicoSolicitante;
    Usuario paciente;
    Exame exame;
    LocalDate dataRealizacaoExame;

    public AutorizacaoExame(int codigo, LocalDateTime dataCadastro, Usuario medicoSolicitante, Usuario paciente, Exame exame) {
        this.codigo = codigo;
        this.dataCadastro = dataCadastro;
        this.medicoSolicitante = medicoSolicitante;
        this.paciente = paciente;
        this.exame = exame;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Usuario getMedicoSolicitante() {
        return medicoSolicitante;
    }

    public Usuario getPaciente() {
        return paciente;
    }

    public Exame getExame() {
        return exame;
    }

    public LocalDate getDataRealizacaoExame() {
        return dataRealizacaoExame;
    }

    public void setDataRealizacaoExame(LocalDate dataRealizacaoExame) {
        this.dataRealizacaoExame = dataRealizacaoExame;
    }
}

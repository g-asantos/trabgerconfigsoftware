package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AutorizacaoExame {
    int codigo;
    LocalDate dataCadastro;
    Usuario medicoSolicitante;
    Usuario paciente;
    Exame exame;
    LocalDate dataRealizacaoExame;

    public AutorizacaoExame(int codigo, LocalDate dataCadastro, Usuario medicoSolicitante, Usuario paciente, Exame exame) {
        this.codigo = codigo;
        this.dataCadastro = dataCadastro;
        this.medicoSolicitante = medicoSolicitante;
        this.paciente = paciente;
        this.exame = exame;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getDataCadastro() {
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataCadastroFormatada = this.getDataCadastro().format(formatter);
        String exameMaisMedico = this.getCodigo() + " - " + this.getExame().getNome() + " solicitado por " + this.getMedicoSolicitante().getNome();
        return exameMaisMedico + " em " + dataCadastroFormatada;
    }
}

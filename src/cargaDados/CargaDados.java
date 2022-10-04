package cargaDados;

import dominio.TipoUsuario;
import repository.ExameRepository;
import repository.UsuarioRepository;

public class CargaDados{
    private UsuarioRepository usuarioRepository;
    private ExameRepository exameRepository;
    
    public CargaDados(UsuarioRepository usuarioRepository, ExameRepository exameRepository) {
        this.usuarioRepository = usuarioRepository;
        this.exameRepository = exameRepository;
}

    public void carregar(){
        usuarioRepository.criar("Jonas Souza",TipoUsuario.ADMIN);
        usuarioRepository.criar("Matheus Oliveira", TipoUsuario.MEDICO);
        usuarioRepository.criar("Thiago Santos", TipoUsuario.PACIENTE);

        exameRepository.criar("ELETROCARDIOGRAMA");
        exameRepository.criar("TESTE ERGOMÉTRICO");
        exameRepository.criar("ECOCARDIOGRAMA");
        exameRepository.criar("ÁCIDO ÚRICO");
        exameRepository.criar("TSH E T4 LIVRE");
        exameRepository.criar("UREIA E CREATINA");
        exameRepository.criar("HEMOGRAMA");
        exameRepository.criar("GLICEMIA EM JEJUM");
        exameRepository.criar("COLESTEROL E TRIGLICERÍDEOS");
        exameRepository.criar("RAIO X");


    }
}

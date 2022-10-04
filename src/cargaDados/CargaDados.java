package cargaDados;

import dominio.TipoUsuario;
import repository.ExameRepository;
import repository.UsuarioRepository;

public class CargaDados{
    private UsuarioRepository usuarioRepository;
    private ExameRepository exameRepository;

    public void carregar(){
        usuarioRepository.criar("Jonas",TipoUsuario.ADMIN);
        usuarioRepository.criar("Matheus", TipoUsuario.MEDICO);
        usuarioRepository.criar("Thiago", TipoUsuario.PACIENTE);

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
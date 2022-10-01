package repositorio;

import java.util.ArrayList;
import java.time.LocalDateTime;  

import dominio.AutorizacaoExame;
import dominio.Exame;
import dominio.Usuario;

public class AutorizacaoRepository {
    private static AutorizacaoRepository instance;
    private static Integer id;
    private ArrayList<AutorizacaoExame> lista;
    /**
     * Método estático que permite a aplicação do padrão Singleton.
     * @return Uma instância da classe AutorizacaoRepository.
     */
    public static AutorizacaoRepository getInstance() {
        if (instance == null) {
            instance = new AutorizacaoRepository();
        }

        return instance;
    }

    private AutorizacaoRepository() {
        lista = new ArrayList<AutorizacaoExame>();
    }
    
    /**
     * Método que cria uma autorização de exame e o insere na lista de autorizações
     * de exame.
     */
    public AutorizacaoExame criar(Usuario paciente, Exame exame, Usuario medico){
        id++;
        LocalDateTime currentTime = java.time.LocalDateTime.now();
        AutorizacaoExame autExame = new AutorizacaoExame(id, currentTime, 
                                                         medico, paciente, exame);
        lista.add(autExame);
        return autExame;    
    }

    /*
     * Método que retorna a lista de autorizações de exame.
     */
    public ArrayList<AutorizacaoExame> listar(){
        return lista;
    }

    /**
     * Método que retorna a lista dos exames já realizados. 
     */
    public ArrayList<AutorizacaoExame> listarRealizados(){
        ArrayList<AutorizacaoExame> listRealizados = new ArrayList<>();
        for (AutorizacaoExame autorizacaoExame : lista) {
            if(autorizacaoExame.getDataRealizacaoExame()!=null){
                listRealizados.add(autorizacaoExame);
            }
        }
        return listRealizados;
    }
    /**
     * Método que retorna a lista de autorizações de exame para um médico específico. 
     */
    public ArrayList<AutorizacaoExame> listarPorMedico(Usuario medico){
        Integer idMedico = medico.getId();
        ArrayList<AutorizacaoExame> listaMedico = new ArrayList<>();
        Integer idLista;
        for (AutorizacaoExame autorizacaoExame : lista) {
            idLista = autorizacaoExame.getMedicoSolicitante().getId();
            if(idLista==idMedico){
                listaMedico.add(autorizacaoExame);
            }
        }
        return listaMedico;
    }
}
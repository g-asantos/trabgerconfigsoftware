package repositorio;

import dominio.Exame;

import java.util.ArrayList;

public class ExameRepository {

    private static ExameRepository instance;
    private static int identificador = 0;
    private final ArrayList<Exame> lista = new ArrayList<>();

    private ExameRepository(){}

    public static ExameRepository getInstance(){
        if(instance == null){
            instance = new ExameRepository();
        }

        return instance;
    }

    public Exame criar(String nome){
        Exame novoExame = new Exame(identificador, nome);
        lista.add(novoExame);
        identificador++;
        return novoExame;
    }

    public ArrayList<Exame> listar(){
        return lista;
    }
}

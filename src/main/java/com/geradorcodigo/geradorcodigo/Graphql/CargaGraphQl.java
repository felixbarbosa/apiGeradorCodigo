package com.geradorcodigo.geradorcodigo.Graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.Carga;
import com.geradorcodigo.geradorcodigo.Model.CargaInput;
import com.geradorcodigo.geradorcodigo.Model.Exercicio;
import com.geradorcodigo.geradorcodigo.Repository.CargaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargaGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CargaRepository cargaRepo;

    public List<Carga> obterCargasPorAluno(int aluno){

        List<Carga> lista = cargaRepo.obterCargasPorAluno(aluno);
        return lista;    
    }

    public Carga obterCargasPorAlunoPorExercicio(int aluno, int exercicio){

        Carga carga = cargaRepo.obterCargaPorAlunoPorExercicio(aluno, exercicio);
        return carga;    
    }

    public Carga salvarCarga(CargaInput cargaInput){
 
        Carga carga = new Carga();
        Aluno aluno = new Aluno();
        Exercicio exercicio = new Exercicio();

        carga.setId(cargaInput.getId());
        carga.setCarga(cargaInput.getCarga());

        aluno.setId(cargaInput.getAluno());
        carga.setAluno(aluno);

        exercicio.setId(cargaInput.getExercicio());
        carga.setExercicio(exercicio);

        carga.setData(cargaInput.getData());

        if(carga.getId() == 0){
            carga = cargaRepo.salvarCarga(carga);
        }
        return carga;
    }
      
}

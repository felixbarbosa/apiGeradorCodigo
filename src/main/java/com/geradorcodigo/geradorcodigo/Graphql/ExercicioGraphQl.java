package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Exercicio;
import com.geradorcodigo.geradorcodigo.Model.ExercicioInput;
import com.geradorcodigo.geradorcodigo.Model.Musculo;
import com.geradorcodigo.geradorcodigo.Repository.ExercicioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExercicioGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ExercicioRepository exercicioRepo;


    public List<Exercicio> obterExercicios(){

        List<Exercicio> lista = exercicioRepo.obterExercicios();
        return lista;    
    }

    public List<Exercicio> obterExerciciosPorMusculo(int musculoId){

        List<Exercicio> lista = exercicioRepo.obterExerciciosPorMusculo(musculoId);
        return lista;    
    }

    public Exercicio obterExercicioPorId(int id){

        Exercicio exercicio = exercicioRepo.obterExercicioPorId(id);
        return exercicio;    
    }

    public Exercicio salvarExercicio(ExercicioInput exercicioInput){
 
        Exercicio exercicio = new Exercicio();
        Musculo musculo = new Musculo();

        exercicio.setId(exercicioInput.getId());
        exercicio.setDescricao(exercicioInput.getDescricao());

        musculo.setId(exercicioInput.getMusculo());
        exercicio.setMusculo(musculo);

        if(exercicio.getId() == 0){
            exercicio = exercicioRepo.salvarExercicio(exercicio);
        }
        return exercicio;
    }
      
}

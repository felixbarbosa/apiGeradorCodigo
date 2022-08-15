package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Exercicio;
import com.geradorcodigo.geradorcodigo.Model.Musculo;
import com.geradorcodigo.geradorcodigo.Model.VariacoesExercicios;
import com.geradorcodigo.geradorcodigo.Model.VariacoesExerciciosInput;
import com.geradorcodigo.geradorcodigo.Repository.VariacoesExerciciosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VariacoesExerciciosGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private VariacoesExerciciosRepository variacoesExerciciosRepo;


    public List<VariacoesExercicios> obterVariacoes(int exercicioId){

        List<VariacoesExercicios> lista = variacoesExerciciosRepo.obterVariacoes(exercicioId);
        return lista;    
    }

    public VariacoesExercicios obterVariacaoPorId(int variacaoId){

        VariacoesExercicios variacao = variacoesExerciciosRepo.obterVariacoesPorId(variacaoId);
        return variacao;    
    }

    public List<VariacoesExercicios> obterVariacoesPorMusculo(int musculoId){

        List<VariacoesExercicios> lista = variacoesExerciciosRepo.obterVariacoesPorMusculo(musculoId);
        return lista;    
    }

    public VariacoesExercicios salvarVariacoesExercicios(VariacoesExerciciosInput variacoesExerciciosInput){
 
        VariacoesExercicios variacoesExercicios= new VariacoesExercicios();
        Exercicio exercicio = new Exercicio();
        Musculo musculo = new Musculo();

        variacoesExercicios.setId(variacoesExerciciosInput.getId());
        variacoesExercicios.setDescricao(variacoesExerciciosInput.getDescricao());

        musculo.setId(variacoesExerciciosInput.getMusculo());
        variacoesExercicios.setMusculo(musculo);

        exercicio.setId(variacoesExerciciosInput.getExercicio());
        variacoesExercicios.setExercicio(exercicio);

        if(variacoesExercicios.getId() == 0){
            variacoesExercicios = variacoesExerciciosRepo.cadastrarVariacao(variacoesExercicios);
        }
        return variacoesExercicios;
    }
      
}

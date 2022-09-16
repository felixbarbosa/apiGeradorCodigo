package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Exercicio;
import com.geradorcodigo.geradorcodigo.Model.Musculo;
import com.geradorcodigo.geradorcodigo.Model.Personal;
import com.geradorcodigo.geradorcodigo.Model.VariacaoExercicio;
import com.geradorcodigo.geradorcodigo.Model.VariacaoExercicioInput;
import com.geradorcodigo.geradorcodigo.Repository.VariacaoExercicioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VariacaoExercicioGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private VariacaoExercicioRepository variacaoExercicioRepo;


    public List<VariacaoExercicio> obterVariacoesExercicios(int professorId){

        List<VariacaoExercicio> lista = variacaoExercicioRepo.obterVariacoesExercicios(professorId);
        return lista;    
    }

    public List<VariacaoExercicio> obterVariacoesExerciciosPorExercicio(int exercicioId, int professorId){

        List<VariacaoExercicio> lista = variacaoExercicioRepo.obterVariaceosExerciciosPorExercicio(exercicioId, professorId);
        return lista;    
    }

    public VariacaoExercicio obterVariacaoExercicioPorId(int id){

        VariacaoExercicio variacaoExercicio = variacaoExercicioRepo.obterVariacaoExercicioPorId(id);
        return variacaoExercicio;    
    }

    public VariacaoExercicio salvarVariacaoExercicio(VariacaoExercicioInput variacaoExercicioInput){

        System.out.println("Salvar dentro do graphql");
 
        Exercicio exercicio = new Exercicio();
        VariacaoExercicio variacaoExercicio = new VariacaoExercicio();
        Musculo musculo = new Musculo();
        Personal professor = new Personal();

        professor.setId(variacaoExercicioInput.getProfessor());
        variacaoExercicio.setProfessor(professor);

        variacaoExercicio.setId(variacaoExercicioInput.getId());
        variacaoExercicio.setDescricao(variacaoExercicioInput.getDescricao());

        musculo.setId(variacaoExercicioInput.getMusculo());
        variacaoExercicio.setMusculo(musculo);

        exercicio.setId(variacaoExercicioInput.getExercicio());
        variacaoExercicio.setExercicio(exercicio);

        variacaoExercicio.setUrlImagem(variacaoExercicioInput.getUrlImagem());
        variacaoExercicio.setUrlVideo(variacaoExercicioInput.getUrlVideo());
        variacaoExercicio.setInstrucao(variacaoExercicioInput.getInstrucao());
        variacaoExercicio.setIsVariacao(variacaoExercicioInput.getIsVariacao());

        if(variacaoExercicio.getId() == 0){
            System.out.println("Chamando o repository...");
            variacaoExercicio = variacaoExercicioRepo.salvarVariacaoExercicio(variacaoExercicio);
        }else{
            variacaoExercicio = variacaoExercicioRepo.atualizar(variacaoExercicio);
        }
        return variacaoExercicio;
    }

    public Boolean deletarVariacaoExercicio(Integer id){

        Boolean removeu = false;

        try{
            removeu = variacaoExercicioRepo.deletar(id);
        }catch(Exception e){

        }

        return removeu;
        
    }
      
}

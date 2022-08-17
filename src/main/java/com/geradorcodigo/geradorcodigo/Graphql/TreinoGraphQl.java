package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.DiaSemana;
import com.geradorcodigo.geradorcodigo.Model.Exercicio;
import com.geradorcodigo.geradorcodigo.Model.Musculo;
import com.geradorcodigo.geradorcodigo.Model.Treino;
import com.geradorcodigo.geradorcodigo.Model.TreinoInput;
import com.geradorcodigo.geradorcodigo.Model.VariacoesExercicios;
import com.geradorcodigo.geradorcodigo.Repository.TreinoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TreinoGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private TreinoRepository treinoRepo;


    public List<Treino> obterTreinoAluno(int alunoId){

        List<Treino> lista = treinoRepo.obterTreinoAluno(alunoId);
        return lista;    
    }

    public List<Treino> obterTreinoAlunoNome(int alunoId, String nome){

        List<Treino> lista = treinoRepo.obterTreinoAlunoNome(alunoId, nome);
        return lista;    
    }

    public List<Treino> obterTreinoAlunoPorDia(int alunoId, int diaSemanaId){

        List<Treino> lista = treinoRepo.obterTreinoAlunoPorDia(alunoId, diaSemanaId);
        return lista;    
    }

    public List<Treino> obterTreinoNomeAluno(int alunoId, int diaSemanaId){

        List<Treino> lista = treinoRepo.obterTreinoNomeAluno(alunoId, diaSemanaId);
        return lista;    
    }

    public List<Treino> obterTreinoAlunoPorMusculo(int alunoId, int musculoId){

        List<Treino> lista = treinoRepo.obterTreinoAlunoPorMusculo(alunoId, musculoId);
        return lista;    
    }

    public Treino salvarTreino(TreinoInput treinoInput){
 
        Treino treino= new Treino();
        Aluno aluno = new Aluno();
        DiaSemana diaSemana = new DiaSemana();
        Exercicio exercicio = new Exercicio();
        Musculo musculo = new Musculo();
        VariacoesExercicios variacao = new VariacoesExercicios();

        treino.setId(treinoInput.getId());

        aluno.setId(treinoInput.getAluno());
        treino.setAluno(aluno);

        treino.setDescanso(treinoInput.getDescanso());

        diaSemana.setId(treinoInput.getDiaSemana());
        treino.setDiaSemana(diaSemana);

        exercicio.setId(treinoInput.getExercicio());
        treino.setExercicio(exercicio);

        musculo.setId(treinoInput.getMusculoAlvo());
        treino.setMusculoAlvo(musculo);

        variacao.setId(treinoInput.getVariacaoExercicio());
        treino.setVariacaoExercicio(variacao);

        treino.setNome(treinoInput.getNome());
        treino.setInstrucao(treinoInput.getInstrucao());

        treino.setRepeticoes(treinoInput.getRepeticoes());
        treino.setSeries(treinoInput.getSeries());
        treino.setVelocidade(treinoInput.getVelocidade());

        if(treino.getId() == 0){
            treino = treinoRepo.salvarTreino(treino);
        }else{
            treino = treinoRepo.atualizar(treino);
        }
        return treino;
    }
      
}

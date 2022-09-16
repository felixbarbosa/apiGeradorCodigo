package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Exercicio;
import com.geradorcodigo.geradorcodigo.Model.VariacaoExercicio;

public interface VariacaoExercicioRepository {
    
    List<VariacaoExercicio> obterVariacoesExercicios(int professorId);

    List<VariacaoExercicio> obterVariaceosExerciciosPorExercicio(int exercicioId, int professorId);

    VariacaoExercicio obterVariacaoExercicioPorId(int id);

    VariacaoExercicio salvarVariacaoExercicio(VariacaoExercicio exercicio);

    VariacaoExercicio atualizar(VariacaoExercicio exercicio);

    Boolean deletar(int id);
}

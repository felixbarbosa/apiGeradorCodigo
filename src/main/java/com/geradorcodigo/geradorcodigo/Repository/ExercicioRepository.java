package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Exercicio;

public interface ExercicioRepository {
    
    List<Exercicio> obterExercicios(int professorId);

    List<Exercicio> obterExerciciosPorMusculo(int musculoId, int professorId);

    Exercicio obterExercicioPorId(int id);

    Exercicio salvarExercicio(Exercicio exercicio);

    Exercicio atualizar(Exercicio exercicio);

    Boolean deletar(int id);
}

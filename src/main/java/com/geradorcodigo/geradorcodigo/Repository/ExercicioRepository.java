package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Exercicio;

public interface ExercicioRepository {
    
    List<Exercicio> obterExercicios();

    List<Exercicio> obterExerciciosPorMusculo(int musculoId);

    Exercicio obterExercicioPorId(int id);

    //List<Exercicio> obterEntregasRealizadas();

    Exercicio salvarExercicio(Exercicio exercicio);

    //Exercicio atualizar(Exercicio entregador);
}

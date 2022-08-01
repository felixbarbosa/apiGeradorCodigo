package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Treino;

public interface TreinoRepository {

    List<Treino> obterTreinoAluno(int alunoId);

    List<Treino> obterTreinoAlunoPorDia(int alunoId, int diaSemanaId);

    List<Treino> obterTreinoAlunoPorMusculo(int alunoId, int musculoId);

    Treino inserirTreinoAluno(Treino treino, int alunoId);

    Treino atualizar(Treino treino);
}

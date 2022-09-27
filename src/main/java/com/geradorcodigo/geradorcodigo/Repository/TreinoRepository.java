package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Treino;

public interface TreinoRepository {

    List<Treino> obterTreinoAluno(int alunoId);

    List<Treino> obterTreinoAlunoNome(int alunoId, String nome);

    List<Treino> obterTreinoAlunoPorDia(int alunoId, int diaSemanaId, String nomeTreino);

    List<Treino> obterTreinoNomeAluno(int alunoId, int diaSemanaId);

    List<Treino> obterTreinoAlunoPorMusculo(int alunoId, int musculoId, String nomeTreino);

    Treino salvarTreino(Treino treino);

    Treino atualizar(Treino treino);
}

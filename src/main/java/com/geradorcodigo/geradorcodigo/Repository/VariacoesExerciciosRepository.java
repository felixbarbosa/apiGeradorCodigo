package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.VariacoesExercicios;

public interface VariacoesExerciciosRepository {
    
    List<VariacoesExercicios> obterVariacoes(int exercicioId);

    List<VariacoesExercicios> obterVariacoesPorMusculo(int musculoId);

    VariacoesExercicios cadastrarVariacao(VariacoesExercicios variacoes);
}

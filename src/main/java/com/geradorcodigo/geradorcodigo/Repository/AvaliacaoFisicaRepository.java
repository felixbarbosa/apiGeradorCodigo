package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;
import com.geradorcodigo.geradorcodigo.Model.AvaliacaoFisica;

public interface AvaliacaoFisicaRepository {
    
    List<AvaliacaoFisica> obterAvaliacaoPorAluno(int alunoId);

    AvaliacaoFisica salvarAvaliacao(AvaliacaoFisica avaliacao);

    //Aluno atualizarAluno(Aluno aluno);
}

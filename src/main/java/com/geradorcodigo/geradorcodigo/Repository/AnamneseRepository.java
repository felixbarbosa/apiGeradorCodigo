package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Anamnese;

public interface AnamneseRepository {
    
    List<Anamnese> obterAnamnesePorAluno(int alunoId);

    Anamnese salvarAnamnese(Anamnese anamnese);

    Boolean deletarAnamnesePorAluno (int alunoId);

    //Aluno atualizarAluno(Aluno aluno);
}

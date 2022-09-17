package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Aluno;

public interface AlunoRepository {
    
    List<Aluno> obterAlunosProfessor(int professorId);

    Aluno obterAlunoPorId(int id);

    Aluno salvarAluno(Aluno aluno);

    Aluno atualizarAluno(Aluno aluno);
}

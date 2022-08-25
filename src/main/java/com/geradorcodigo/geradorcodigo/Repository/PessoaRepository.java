package com.geradorcodigo.geradorcodigo.Repository;
import com.geradorcodigo.geradorcodigo.Model.Pessoa;

public interface PessoaRepository {

    Pessoa obterPessoa(int id);

    Pessoa salvarPessoa(Pessoa pessoa);

    //Aluno atualizarAluno(Aluno aluno);
}

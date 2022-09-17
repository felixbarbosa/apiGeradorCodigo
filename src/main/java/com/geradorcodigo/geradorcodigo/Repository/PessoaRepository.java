package com.geradorcodigo.geradorcodigo.Repository;
import com.geradorcodigo.geradorcodigo.Model.Pessoa;

public interface PessoaRepository {

    Pessoa obterPessoaPorId(int id);

    Pessoa obterPessoaPorPersonal(int personalId);

    Pessoa obterPessoaPorAluno(int alunoId);

    Pessoa salvarPessoa(Pessoa pessoa);
}

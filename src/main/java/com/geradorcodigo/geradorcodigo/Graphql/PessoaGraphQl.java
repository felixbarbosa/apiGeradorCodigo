package com.geradorcodigo.geradorcodigo.Graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.Personal;
import com.geradorcodigo.geradorcodigo.Model.Pessoa;
import com.geradorcodigo.geradorcodigo.Model.PessoaInput;
import com.geradorcodigo.geradorcodigo.Model.Usuario;
import com.geradorcodigo.geradorcodigo.Repository.PessoaRepository;
import com.geradorcodigo.geradorcodigo.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PessoaRepository pessoaRepo;

    public Pessoa obterPessoa(int id){

        Pessoa pessoa = pessoaRepo.obterPessoa(id);
        return pessoa;    
    }

    public Pessoa salvarPessoa(PessoaInput pessoaInput){
 
        Pessoa pessoa = new Pessoa();
        Personal personal = new Personal();
        Aluno aluno = new Aluno();

        pessoa.setId(pessoaInput.getId());
        pessoa.setCpf(pessoaInput.getCpf());
        pessoa.setEmail(pessoaInput.getEmail());
        pessoa.setNome(pessoaInput.getNome());
        pessoa.setCref(pessoaInput.getCref());
        pessoa.setCpf(pessoaInput.getCpf());

        personal.setId(pessoaInput.getPersonal());
        pessoa.setPersonal(personal);

        aluno.setId(pessoaInput.getAluno());
        pessoa.setAluno(aluno);

        if(pessoa.getId() == 0){
            pessoa = pessoaRepo.salvarPessoa(pessoa);
        }
        return pessoa;
}
      
}

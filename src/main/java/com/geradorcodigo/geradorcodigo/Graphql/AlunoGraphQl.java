package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.AlunoInput;
import com.geradorcodigo.geradorcodigo.Model.Personal;
import com.geradorcodigo.geradorcodigo.Repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlunoGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private AlunoRepository alunoRepo;


    public List<Aluno> obterAlunosProfessor(int professorId){

        List<Aluno> lista = alunoRepo.obterAlunosProfessor(professorId);
        return lista;    
    }

    public Aluno obterAlunoPorId(int id){

        Aluno aluno = alunoRepo.obterAlunoPorId(id);
        return aluno;    
    }

    public Aluno salvarAluno(AlunoInput alunoInput){
 
        Aluno aluno= new Aluno();
        Personal personal = new Personal();

        aluno.setId(alunoInput.getId());
        aluno.setCpf(alunoInput.getCpf());
        aluno.setEmail(alunoInput.getEmail());
        aluno.setIdade(alunoInput.getIdade());
        aluno.setLogin(alunoInput.getLogin());
        aluno.setNome(alunoInput.getNome());

        personal.setId(alunoInput.getPersonal());
        aluno.setPersonal(personal);

        aluno.setSenha(alunoInput.getSenha());
        aluno.setSexo(alunoInput.getSexo());

        if(aluno.getId() == 0){
            aluno = alunoRepo.salvarAluno(aluno);
        }
        return aluno;
    }
      
}
